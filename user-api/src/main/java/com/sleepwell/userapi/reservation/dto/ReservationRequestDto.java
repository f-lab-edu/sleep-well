package com.sleepwell.userapi.reservation.dto;

import com.sleepwell.userapi.reservation.entity.Reservation;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
public class ReservationRequestDto {

    private Long accommodationId;

    private Long guestId;

    private String accommodationName;

    private String paymentType;

    private String accommodationType;

    private String location;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkInDate;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate checkOutDate;

    private int numberOfGuest;

    public Reservation toEntity() {
        return new Reservation(this.paymentType, checkInDate, checkOutDate, numberOfGuest);
    }
}
