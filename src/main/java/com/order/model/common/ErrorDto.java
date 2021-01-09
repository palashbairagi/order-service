package com.order.model.common;

import com.order.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorDto extends Dto {

    private ErrorCode errorCode;

    private String code;

    private String message;

    public ErrorDto(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

}
