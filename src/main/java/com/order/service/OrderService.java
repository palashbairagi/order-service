package com.order.service;

import com.order.model.dto.OrderDto;
import com.order.model.dto.OrderStatusDto;

import java.util.List;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

    OrderDto getOrder(Long orderId);

    Void cancelOrder(Long orderId);

    List<OrderDto> createOrders(List<OrderDto> orderRequests);

    Void updateStatus(List<OrderStatusDto> orderStatusDtos);
    
}
