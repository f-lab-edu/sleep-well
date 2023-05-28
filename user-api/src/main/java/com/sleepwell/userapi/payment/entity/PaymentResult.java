package com.sleepwell.userapi.payment.entity;

import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentResult {

    @Id @GeneratedValue
    @Column(name = "PAYMENT_ID")
    private Long id;

    private Long impUid;

    private int amount;

    @Enumerated
    private PaymentStatus paymentStatus;

    private Date paidAt;

    @OneToOne
    @JoinColumn(name = "paymentResult")
    private Reservation reservation;

    public PaymentResult(Long impUid, int amount, PaymentStatus paymentStatus, Date paidAt, Reservation reservation) {
        this.impUid = impUid;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.paidAt = paidAt;
        this.reservation = reservation;
    }
}
