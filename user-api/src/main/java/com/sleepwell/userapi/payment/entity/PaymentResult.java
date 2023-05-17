package com.sleepwell.userapi.payment.entity;

import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.Date;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PaymentResult {

    //TODO: impUid 가 unique 한지 확인
    @Id
    @GeneratedValue
    private Long impUid;

    private int amount;

    @Enumerated
    private PaymentStatus paymentStatus;

    private Date paidAt;

    @OneToOne
    private Reservation reservation;

}
