package com.order.client.config;

import com.order.enums.ErrorCode;
import com.order.exception.OrderException;
import com.order.model.common.ResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RestClient {

    private final RestTemplate restTemplate;

    public <R, T> ResponseDto<T> exchange(URI requestUri, HttpMethod method, HttpEntity<R> httpEntity,
                                                  ParameterizedTypeReference<ResponseDto<T>> responseType) {
        return handleResponse(restTemplate.exchange(requestUri, method, httpEntity, responseType));
    }


    private <T> ResponseDto<T> handleResponse(ResponseEntity<ResponseDto<T>> responseEntity) {

        if (responseEntity == null || responseEntity.getBody() == null) {
            log.error("Response entity is null");
            throw new OrderException(ErrorCode.SERVER_ERROR);
        }

        ResponseDto<T> responseDto = responseEntity.getBody();
        HttpStatus status = responseEntity.getStatusCode();

        if(!status.isError()) {
            return responseDto;
        } else {
            log.error("Errors : {}", responseDto.getErrors());
            throw new OrderException(ErrorCode.GENERAL_INPUT_ERROR);
        }

    }

}
