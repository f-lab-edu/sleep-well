package com.sleepwell.userapi.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class PaymentRequestDto {

    @NotNull
    @Pattern(message = "유효하지 않은 결제 ID 형식입니다.", regexp = "^imp_\\d+$")
    private final String imp_uid;

    @NotNull
    private final Long merchant_uid;

}
