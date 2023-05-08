package com.sleepwell.userapi.reservation.entity;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.payment.entity.PaymentResult;
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

    private LocalDate reservedDate;

    private ReservationStatus reservationStatus;

    private int numberOfGuest;

    private int amount;

    private Member guest;

    private Accommodation accommodation;

    private PaymentResult paymentResult;

    public Reservation(String paymentType, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuest, int amount) {
        this.paymentType = paymentType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuest = numberOfGuest;
        this.amount = amount;
    }

    public void updateReservation(Member guest, Accommodation accommodation) {
        this.setGuest(guest);
        this.setAccommodation(accommodation);
        guest.getReservations().add(this);
        accommodation.getReservations().add(this);
    }

    public void updatePayment(PaymentResult paymentResult) {
        this.paymentResult = paymentResult;
        this.reservationStatus = ReservationStatus.RESERVED;
    }

    public void cancelReservation() {
        accommodation.getReservations().remove(this);
        this.setReservationStatus(ReservationStatus.CANCELED);
    }
}
