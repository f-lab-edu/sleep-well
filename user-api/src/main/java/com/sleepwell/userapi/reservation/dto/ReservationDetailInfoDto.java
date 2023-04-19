package com.sleepwell.userapi.reservation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationDetailInfoDto {

    private Long reservationId;

    private Long accommodationId;

    private String accommodationName;

    private String hostName;

    private String guestName;

    private int price;

    private String paymentType;

    private String accommodationType;

    private String location;

    private String checkInDate;

    private String checkOutDate;

    private String checkInTime;

    private String checkOutTime;

    private Integer guests;

    private String reservationStatus;
}
