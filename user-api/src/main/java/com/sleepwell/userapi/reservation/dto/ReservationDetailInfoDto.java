package com.sleepwell.userapi.reservation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationDetailInfoDto {

    private Long reservationId;

    private Long accommodationId;

    private String accommodationName;

    private String type;

    private String location;

    private String checkInDate;

    private String checkOutDate;

    private Integer guests;
}
