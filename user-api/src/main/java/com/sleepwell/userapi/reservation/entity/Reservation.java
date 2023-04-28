package com.sleepwell.userapi.reservation.entity;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.member.entity.Member;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Reservation {

    private Long id;

    private String paymentType;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private int numberOfGuest;

    private String reservationStatus;

    private Member guest;

    private Accommodation accommodation;

    public Reservation(String paymentType, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuest) {
        this.paymentType = paymentType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuest = numberOfGuest;
    }

    public void updateReservation(Member guest, Accommodation accommodation) {
        this.setGuest(guest);
        this.setAccommodation(accommodation);
        guest.getReservations().add(this);
        accommodation.getReservations().add(this);
    }
}
