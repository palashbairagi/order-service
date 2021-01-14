package com.order.mapper;

import com.order.model.dto.OrderDto;
import com.order.model.entity.Order;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface OrderMapper {

    OrderDto map(Order order);

    Order map(OrderDto orderDto);

}
