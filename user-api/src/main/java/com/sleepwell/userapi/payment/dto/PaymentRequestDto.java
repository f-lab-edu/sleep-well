package com.sleepwell.userapi.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentRequestDto {

    private final String impUid;

    private final String merchantUid;

}
