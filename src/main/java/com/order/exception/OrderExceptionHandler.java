package com.order.exception;

import com.order.model.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderExceptionHandler {

    private final ResponseResolver responseResolver;

    @ExceptionHandler(value = OrderException.class)
    public ResponseEntity handleOrderException(OrderException orderException) {

        ResponseEntity<ResponseDto<?>> responseEntity = responseResolver.resolve(orderException);
        return new ResponseEntity(responseEntity.getBody(), responseEntity.getStatusCode());

    }

    // TODO: Handle other exceptions

}
