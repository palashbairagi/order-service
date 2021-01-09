package com.order.model.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Getter
@AllArgsConstructor
public class HttpErrorResponse extends BaseModel {

    private HttpStatus httpStatus;
    private ErrorDto errorDto;

}
