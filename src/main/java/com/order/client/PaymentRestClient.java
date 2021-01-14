package com.order.client;

import com.order.model.common.ResponseDto;
import com.order.model.dto.OrderDto;

public interface PaymentRestClient {

    ResponseDto<OrderDto.Payment> addPayment(OrderDto.Payment payment);

}
