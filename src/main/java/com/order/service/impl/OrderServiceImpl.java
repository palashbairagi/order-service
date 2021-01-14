package com.order.service.impl;

import com.order.client.PaymentRestClient;
import com.order.mapper.OrderMapper;
import com.order.model.common.ResponseDto;
import com.order.model.dto.OrderDto;
import com.order.model.entity.Order;
import com.order.repository.OrderRepository;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;
    private final OrderRepository orderRepository;
    private final PaymentRestClient paymentRestClient;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        Order order = orderMapper.map(orderDto);

        Order order1 = orderRepository.save(order);

        OrderDto orderDto1 = orderMapper.map(order1);

        List<ResponseDto<OrderDto.Payment>> paymentResponseDto = orderDto.getPayments().stream()
                .map(payment -> paymentRestClient.addPayment(payment))
                .collect(Collectors.toList());

        return orderDto1;
    }

}
