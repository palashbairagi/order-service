package com.order.controller;

import com.order.enums.ErrorCode;
import com.order.exception.OrderException;
import com.order.model.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderController {

    @GetMapping
    public ResponseDto getOrder() {

        throw new OrderException(ErrorCode.GENERAL_INPUT_ERROR);

    }

}
