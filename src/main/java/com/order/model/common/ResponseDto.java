package com.order.model.common;

import com.order.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto<T> extends Dto {

    private ResponseStatus status;
    private T data;
    private ErrorDto errors;

    public static <T> ResponseDto<T> forSuccess(T data) {
        return new ResponseDto<>(ResponseStatus.SUCCESS, data, null);
    }

    public static <T> ResponseDto<T> forError(ErrorDto error) {
        return new ResponseDto<>(ResponseStatus.ERROR, null, error);
    }

}
