package com.sleepwell.userapi.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentRequestDto {

    private final String imp_uid;

    private final String merchant_uid;

}
