package com.sleepwell.userapi.payment.entity;

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

    public PaymentResult(Long impUid, BigDecimal amount, PaymentStatus paymentStatus, Date paidAt) {
        this.impUid = impUid;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paidAt = paidAt;
    }
}
