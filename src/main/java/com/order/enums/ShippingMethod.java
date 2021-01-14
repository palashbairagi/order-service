package com.order.enums;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum ShippingMethod {

    IN_STORE_PICKUP,
    CURBSIDE_DELIVERY,
    HOME_DELIVERY,
    THIRD_PARTY_DELIVERY;

    public String getKey() {
        return this.name();
    }

    public static boolean isValidShippingMethod(final String shippingMethod) {

        return Arrays.stream(ShippingMethod.values())
                .map(ShippingMethod::name)
                .collect(Collectors.toSet())
                .contains(shippingMethod);

    }
}
