package com.order.repository;

import com.order.enums.OrderStatus;
import com.order.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Modifying
    @Query("update Order p set p.status = :status where p.orderId = :orderId")
    Integer updateOrderStatus(@Param("status") OrderStatus status, @Param("orderId") Long orderId);

}
