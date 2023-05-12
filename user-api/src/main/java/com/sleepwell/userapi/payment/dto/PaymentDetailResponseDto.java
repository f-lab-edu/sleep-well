package com.sleepwell.userapi.payment.dto;

import com.sleepwell.userapi.payment.entity.PaymentResult;
import com.sleepwell.userapi.payment.entity.PaymentStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class PaymentDetailResponseDto {

    private final Long impUid;

    private final Long reservationId;

    private final int amount;

    private final PaymentStatus paymentStatus;

    private final Date paidAt;

    public static PaymentDetailResponseDto fromEntity(PaymentResult paymentResult) {
        return PaymentDetailResponseDto.builder()
                .impUid(paymentResult.getImpUid())
                .reservationId(paymentResult.getReservation().getId())
                .amount(paymentResult.getAmount())
                .paymentStatus(paymentResult.getPaymentStatus())
                .paidAt(paymentResult.getPaidAt())
                .build();
    }
}
