package com.order.client.impl;

import com.order.client.PaymentRestClient;
import com.order.client.config.RestClient;
import com.order.config.PaymentServiceProperties;
import com.order.model.common.ResponseDto;
import com.order.model.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
@Slf4j
public class PaymentRestClientImpl extends RestClient implements PaymentRestClient {

    private final PaymentServiceProperties paymentServiceProperties;

    public PaymentRestClientImpl(RestTemplate restTemplate, PaymentServiceProperties paymentServiceProperties) {
        super(restTemplate);
        this.paymentServiceProperties = paymentServiceProperties;
    }

    @Override
    public ResponseDto<OrderDto.Payment> addPayment(OrderDto.Payment payment) {

        URI uri = paymentServiceProperties.addPayment();
        log.info("Sending request to : {}", uri);

        return exchange(uri, HttpMethod.POST, new HttpEntity<>(payment), new ParameterizedTypeReference<ResponseDto<OrderDto.Payment>>() {
        });
    }

}
