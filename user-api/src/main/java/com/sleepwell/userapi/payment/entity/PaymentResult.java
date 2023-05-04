package com.sleepwell.userapi.payment.entity;

import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class PaymentResult {

    private Long impUid;

    private int amount;

    private PaymentStatus paymentStatus;

    private Date paidAt;

    private Reservation reservation;

}
