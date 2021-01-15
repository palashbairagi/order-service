package com.order.controller;

import com.order.model.common.ResponseDto;
import com.order.model.dto.OrderDto;
import com.order.model.dto.OrderStatusDto;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<ResponseDto<OrderDto>> createOrder(@RequestBody OrderDto orderRequestDto) {
        return new ResponseEntity<>(ResponseDto.forSuccess(orderService.createOrder(orderRequestDto)), HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ResponseDto<OrderDto>> getOrder(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(ResponseDto.forSuccess(orderService.getOrder(orderId)), HttpStatus.OK);
    }

    @PutMapping("/order/{orderId}")
    public ResponseEntity<ResponseDto<Void>> cancelOrder(@PathVariable("orderId") Long orderId) {
        return new ResponseEntity<>(ResponseDto.forSuccess(orderService.cancelOrder(orderId)), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<ResponseDto<List<OrderDto>>> createOrders(@RequestBody List<OrderDto> orderRequests) {
        return new ResponseEntity<>(ResponseDto.forSuccess(orderService.createOrders(orderRequests)), HttpStatus.OK);
    }

    @PutMapping("/orders")
    public ResponseEntity<ResponseDto<Void>> updateOrders(@RequestBody List<OrderStatusDto> orderStatusDtos) {
        return new ResponseEntity<>(ResponseDto.forSuccess(orderService.updateStatus(orderStatusDtos)), HttpStatus.OK);
    }

}
