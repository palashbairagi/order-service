package com.order.model.entity;

import com.order.constants.Constants;
import com.order.enums.OrderStatus;
import com.order.enums.ShippingMethod;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Constants.TBL_ORDER_INFO)
@Data
public class Order extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "sub_total")
    private Double subTotal;

    @Column(name = "tax")
    private Double tax;

    @Column(name = "shipping_charges")
    private Double shippingCharges;

    @Column(name = "total")
    private Double total;

    @Column(name = "shipping_method")
    @Enumerated(EnumType.STRING)
    private ShippingMethod shippingMethod;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = Constants.TBL_ORDER_ITEMS_INFO,
            joinColumns = { @JoinColumn(name = "order_id") },
            inverseJoinColumns = { @JoinColumn(name = "item_id") })
    private Set<Item> items = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private ShippingAddress shippingAddress;

}

