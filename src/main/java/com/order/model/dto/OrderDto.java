package com.order.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.order.enums.OrderStatus;
import com.order.model.common.Dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class OrderDto extends Dto {

    private Long orderId;

    private Long customerId;

    private OrderStatus status = OrderStatus.PENDING;

    private List<Item> items;

    private Double subTotal;

    private Double tax;

    private Double shippingCharges;

    private Double total;

    private String shippingMethod;

    private ShippingAddress shippingAddress;

    private List<Payment> payments;

    @Setter
    @Getter
    public static class Item extends Dto {

        private Long itemId;

        private String name;

    }

    @Setter
    @Getter
    public static class ShippingAddress extends Dto {

        private Long addressId;

        private String line1;

        private String line2;

        private String city;

        private String state;

        private String zip;

    }

    @Setter
    @Getter
    public static class Payment extends Dto {

        private Long confirmationNumber;

        private LocalDate paymentDate;

        private String paymentMethod;

        private Long orderId;

        private String status;

        private Double amount;

        private BillingAddress billingAddress;

        @Setter
        @Getter
        public static class BillingAddress extends Dto {

            private Long addressId;

            private String line1;

            private String line2;

            private String city;

            private String state;

            private String zip;

        }

    }


}
