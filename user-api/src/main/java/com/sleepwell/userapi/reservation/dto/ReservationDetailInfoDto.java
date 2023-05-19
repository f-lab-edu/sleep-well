package com.sleepwell.userapi.reservation.dto;

import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.entity.ReservationStatus;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Builder
public class ReservationDetailInfoDto {

    private final Long reservationId;

    private final Long accommodationId;

    private final String accommodationName;

    private final String hostName;

    private final String guestName;

    private final int price;

    private final String accommodationType;

    private final String location;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate checkInDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate checkOutDate;

    @DateTimeFormat(pattern = "hh:mm")
    private final LocalTime checkInTime;

    @DateTimeFormat(pattern = "hh:mm")
    private final LocalTime checkOutTime;

    private final int numberOfGuest;

    private final ReservationStatus reservationStatus;

    public static ReservationDetailInfoDto fromEntity(Reservation reservation) {
        return ReservationDetailInfoDto.builder()
                .reservationId(reservation.getId())
                .accommodationId(reservation.getAccommodation().getId())
                .accommodationName(reservation.getAccommodation().getAccommodationName())
                .hostName(reservation.getAccommodation().getHost().getName())
                .guestName(reservation.getGuest().getName())
                .price(reservation.getAccommodation().getPrice())
                .accommodationType(reservation.getAccommodation().getAccommodationType())
                .location(reservation.getAccommodation().getLocation())
                .checkInDate(reservation.getCheckInDate())
                .checkOutDate(reservation.getCheckOutDate())
                .checkInTime(reservation.getAccommodation().getCheckInTime())
                .checkOutTime(reservation.getAccommodation().getCheckOutTime())
                .numberOfGuest(reservation.getNumberOfGuest())
                .reservationStatus(reservation.getReservationStatus())
                .build();
    }
}
