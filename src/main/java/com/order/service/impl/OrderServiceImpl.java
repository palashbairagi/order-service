package com.order.service.impl;

import com.order.client.PaymentRestClient;
import com.order.enums.ErrorCode;
import com.order.enums.OrderStatus;
import com.order.enums.ShippingMethod;
import com.order.exception.OrderException;
import com.order.mapper.OrderMapper;
import com.order.model.dto.OrderDto;
import com.order.model.dto.OrderStatusDto;
import com.order.model.entity.Order;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final PaymentRestClient paymentRestClient;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        if (!ShippingMethod.isValidShippingMethod(orderDto.getShippingMethod()) ||
                (!orderDto.getShippingMethod().equalsIgnoreCase(ShippingMethod.IN_STORE_PICKUP.getKey())
                        && Objects.isNull(orderDto.getShippingAddress()))) {

            log.error("Invalid Shipping Method");
            throw new OrderException(ErrorCode.REQUEST_MALFORMED);
        }

        Order order = orderMapper.map(orderDto);

        OrderDto orderResponseDto = orderMapper.map(orderRepository.save(order));

        List<OrderDto.Payment> payments = orderDto.getPayments().stream()
                .map(payment -> {
                    payment.setOrderId(order.getOrderId());
                    return payment;
                })
                .collect(Collectors.toList());

        orderResponseDto.setPayments(paymentRestClient.addPayment(payments).getData());

        OrderStatusDto orderStatusDto = new OrderStatusDto(order.getOrderId(), OrderStatus.RECEIVED);
        updateStatus(Arrays.asList(orderStatusDto));
        orderResponseDto.setStatus(OrderStatus.RECEIVED);

        return orderResponseDto;
    }

    @Override
    public OrderDto getOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(ErrorCode.REQUEST_MALFORMED));

        OrderDto orderResponse = orderMapper.map(order);
        orderResponse.setPayments(paymentRestClient.getPayments(orderId).getData());

        return orderResponse;
    }

    @Override
    public Void cancelOrder(Long orderId) {

        orderRepository.updateOrderStatus(OrderStatus.CANCELLED, orderId);
        paymentRestClient.processRefund(orderId);

        return null;
    }

    @Override
    public List<OrderDto> createOrders(List<OrderDto> orderRequests) {

        return orderRequests.stream()
                .map(this::createOrder)
                .collect(Collectors.toList());

    }

    @Override
    public Void updateStatus(List<OrderStatusDto> orderStatusDtos) {

        orderStatusDtos.forEach(orderStatusDto -> {

            if (OrderStatus.isValidOrderStatus(orderStatusDto.getStatus().getKey())) {

                orderRepository.updateOrderStatus(orderStatusDto.getStatus(), orderStatusDto.getOrderId());

                if (OrderStatus.CANCELLED == orderStatusDto.getStatus()) {
                    paymentRestClient.processRefund(orderStatusDto.getOrderId());
                }
            }

        });

        return null;
    }

}
