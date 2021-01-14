package com.order.model.dto;

import com.order.model.common.Dto;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class OrderDto extends Dto {

    private Long customerId;

    private Set<Item> items;

    private Double subTotal;

    private Double tax;

    private Double shippingCharges;

    private Double total;

    private String shippingMethod;

    private ShippingAddress shippingAddress;

    private Set<Payment> payments;

    @Data
    public static class Item extends Dto {

        private Long itemId;

        private String name;

    }

    @Data
    public static class ShippingAddress extends Dto {

        private Long addressId;

        private String line1;

        private String line2;

        private String city;

        private String state;

        private String zip;

    }

    @Data
    public static class Payment extends Dto {

        private Long confirmationNumber;

        private LocalDate paymentDate;

        private String paymentMethod;

        private Long orderId;

        private String status;

        private Double amount;

        private BillingAddress billingAddress;

        @Data
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
