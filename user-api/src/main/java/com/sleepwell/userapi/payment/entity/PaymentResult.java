package com.sleepwell.userapi.payment.entity;

import com.siot.IamportRestClient.response.Payment;
import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class PaymentResult {

    private Long impUid;

    private BigDecimal amount;

    private PaymentStatus paymentStatus;

    private Date paidAt;

    private Reservation reservation;

    public PaymentResult(Payment payment) {
        this.impUid = Long.valueOf(payment.getImpUid());
        this.amount = payment.getAmount();
        this.paymentStatus = PaymentStatus.valueOf(payment.getStatus());
        this.paidAt = payment.getPaidAt();
    }
}
