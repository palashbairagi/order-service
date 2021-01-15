package com.order.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Configuration
@ConfigurationProperties(prefix = "services.payment-service")
@Data
public class PaymentServiceProperties {

    private String host;

    private PaymentServiceProperties.Endpoints endpoints;

    public URI paymentUri() {
        return UriComponentsBuilder
                .fromUriString(this.getHost())
                .path(this.getEndpoints().getPayment())
                .build()
                .toUri();
    }

    @Data
    public static class Endpoints {
        private String payment;
    }

}
