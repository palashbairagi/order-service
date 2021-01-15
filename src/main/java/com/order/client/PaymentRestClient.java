package com.order.client;

import com.order.model.common.ResponseDto;
import com.order.model.dto.OrderDto;

import java.util.List;

public interface PaymentRestClient {

    ResponseDto<List<OrderDto.Payment>> addPayment(List<OrderDto.Payment> payments);

    ResponseDto<List<OrderDto.Payment>> getPayments(Long orderId);

    ResponseDto<Void> processRefund(Long orderId);

}
