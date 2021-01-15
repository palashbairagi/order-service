package com.order.client.impl;

import com.order.client.PaymentRestClient;
import com.order.client.config.RestClient;
import com.order.config.PaymentServiceProperties;
import com.order.model.common.ResponseDto;
import com.order.model.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
@Slf4j
public class PaymentRestClientImpl extends RestClient implements PaymentRestClient {

    private final PaymentServiceProperties paymentServiceProperties;

    public PaymentRestClientImpl(RestTemplate restTemplate, PaymentServiceProperties paymentServiceProperties) {
        super(restTemplate);
        this.paymentServiceProperties = paymentServiceProperties;
    }

    @Override
    public ResponseDto<List<OrderDto.Payment>> addPayment(List<OrderDto.Payment> payment) {

        URI uri = paymentServiceProperties.paymentUri();
        log.info("Sending request to : {}", uri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return exchange(uri, HttpMethod.POST, new HttpEntity<>(payment, headers), new ParameterizedTypeReference<ResponseDto<List<OrderDto.Payment>>>() {
        });
    }

    @Override
    public ResponseDto<List<OrderDto.Payment>> getPayments(Long orderId) {

        URI uri = paymentServiceProperties.paymentUri().resolve(orderId.toString());
        log.info("Sending request to : {}", uri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return exchange(uri, HttpMethod.GET, new HttpEntity<>(null, headers), new ParameterizedTypeReference<ResponseDto<List<OrderDto.Payment>>>() {
        });
    }

    @Override
    public ResponseDto<Void> processRefund(Long orderId) {

        URI uri = paymentServiceProperties.paymentUri().resolve(orderId.toString());
        log.info("Sending request to : {}", uri);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return exchange(uri, HttpMethod.PUT, new HttpEntity<>(null, headers), new ParameterizedTypeReference<ResponseDto<Void>>() {
        });
    }

}
