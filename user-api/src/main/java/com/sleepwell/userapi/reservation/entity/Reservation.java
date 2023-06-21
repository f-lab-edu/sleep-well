package com.sleepwell.userapi.reservation.entity;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.payment.entity.PaymentResult;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESERVATION_ID")
    private Long id;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private LocalDate reservedDate;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus;

    private int numberOfGuest;

    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ACCOMMODATION_ID")
    private Accommodation accommodation;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "reservation")
    private PaymentResult paymentResult;

    public Reservation(LocalDate checkInDate, LocalDate checkOutDate, LocalDate reservedDate, ReservationStatus reservationStatus, int numberOfGuest, int amount) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.reservedDate = reservedDate;
        this.reservationStatus = reservationStatus;
        this.numberOfGuest = numberOfGuest;
        this.amount = amount;
    }

    public void updateReservation(Member guest, Accommodation accommodation) {
        this.setGuest(guest);
        this.setAccommodation(accommodation);
        accommodation.getReservations().add(this);
    }

    public void updatePayment(PaymentResult paymentResult) {
        this.paymentResult = paymentResult;
        this.reservationStatus = ReservationStatus.RESERVED;
    }

    public void cancelReservation() {
        this.setReservationStatus(ReservationStatus.CANCELED);
    }
}
