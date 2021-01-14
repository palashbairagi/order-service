package com.order.exception;

import com.order.enums.ErrorCode;
import com.order.model.common.ErrorDto;
import com.order.model.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class OrderExceptionHandler {

    private final ResponseResolver responseResolver;

    @ExceptionHandler(value = OrderException.class)
    public ResponseEntity handleOrderException(OrderException orderException) {
        log.error("{} exception thrown with message: {}", orderException.getClass().getSimpleName(), orderException.getMessage());
        ResponseEntity<ResponseDto<?>> responseEntity = responseResolver.resolve(orderException);
        return new ResponseEntity(responseEntity.getBody(), responseEntity.getStatusCode());

    }

    // TODO: Handle other exceptions

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity exception(Exception exception) {
        log.error("{} exception thrown with message: {}", exception.getClass().getSimpleName(), exception.getMessage());
        ErrorDto errorDto = new ErrorDto(ErrorCode.SERVER_ERROR);
        return new ResponseEntity(ResponseDto.forError(errorDto), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
