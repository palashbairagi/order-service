package com.order.controller;

import com.order.model.common.ResponseDto;
import com.order.model.dto.OrderDto;
import com.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<ResponseDto<OrderDto>> createOrder(@RequestBody OrderDto orderRequestDto) {
        return new ResponseEntity<>(ResponseDto.forSuccess(orderService.createOrder(orderRequestDto)), HttpStatus.OK);
    }

}
