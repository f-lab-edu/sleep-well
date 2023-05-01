package com.sleepwell.userapi.reservation.dto;

import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReservationRequestDto {

    private final Long accommodationId;

    private final Long guestId;

    private final String accommodationName;

    private final String paymentType;

    private final String accommodationType;

    private final String location;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate checkInDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private final LocalDate checkOutDate;

    private final int numberOfGuest;

    public Reservation toReservation() {
        return new Reservation(this.paymentType, checkInDate, checkOutDate, numberOfGuest);
    }
}
