package com.order.model.dto;

import com.order.enums.OrderStatus;
import com.order.model.common.Dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatusDto extends Dto {

    private Long orderId;

    private OrderStatus status;

}
