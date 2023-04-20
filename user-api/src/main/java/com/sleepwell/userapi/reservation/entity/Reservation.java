package com.sleepwell.userapi.reservation.entity;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Reservation {

    private Long id;

    private String paymentType;

    private String checkInDate;

    private String checkOutDate;

    private int numberOfGuest;

    private Member guest;

    private Accommodation accommodation;
}
