package com.sleepwell.userapi.reservation.dto;

import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.entity.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ReservationRequestDto {

    @NotNull
    private final Long accommodationId;

    @NotNull
    private final Long guestId;

    @NotBlank
    private final String accommodationName;

    private final int amount;

    @NotBlank
    private final String accommodationType;

    @NotBlank
    private final String location;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate checkInDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private final LocalDate checkOutDate;

    @Min(1)
    private final int numberOfGuest;

    public Reservation toEntity() {
        return new Reservation(checkInDate, checkOutDate, LocalDate.now(), ReservationStatus.BEFORE_PAYED, numberOfGuest, amount);
    }
}
