package com.order.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum OrderStatus {

    PENDING,
    RECEIVED,
    SHIPPED,
    READY_FOR_PICKUP,
    DELIVERED,
    CANCELLED;

    public String getKey() {
        return this.name();
    }

    public static boolean isValidOrderStatus(final String orderStatus) {

        return Arrays.stream(OrderStatus.values())
                .map(OrderStatus::name)
                .collect(Collectors.toSet())
                .contains(orderStatus);

    }
}
