package com.sleepwell.userapi.reservation.entity;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.payment.entity.PaymentResult;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id
    @GeneratedValue
    private Long id;

    //TODO: PaymentType을 적용시킬지, 적용시킨다면 어떻게 적용시킬지 고민
    private String paymentType;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private LocalDate reservedDate;

    @Enumerated
    private ReservationStatus reservationStatus;

    private int numberOfGuest;

    private int amount;

    @OneToOne
    private Member guest;

    @OneToOne
    private Accommodation accommodation;

    @OneToOne
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
