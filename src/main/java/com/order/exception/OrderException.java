package com.order.exception;

import com.order.enums.ErrorCode;
import com.order.model.common.ErrorDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OrderException extends RuntimeException {

    private ErrorDto error;

    public OrderException(ErrorCode errorCode) {
        this.error = new ErrorDto(errorCode);
    }

}
