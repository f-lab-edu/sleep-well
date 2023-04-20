package com.sleepwell.userapi.reservation.entity;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.reservation.dto.ReservationDetailInfoDto;
import lombok.Builder;
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

    @Builder
    public Reservation(String paymentType, LocalDate checkInDate, LocalDate checkOutDate, int numberOfGuest) {
        this.paymentType = paymentType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfGuest = numberOfGuest;
    }

    public void createReservation(Member guest, Accommodation accommodation) {
        this.setGuest(guest);
        this.setAccommodation(accommodation);
        guest.getReservations().add(this);
        accommodation.getReservations().add(this);
    }

    public ReservationDetailInfoDto toReservationDetailInfoDto() {
        return ReservationDetailInfoDto.builder()
                .reservationId(this.id)
                .accommodationId(this.accommodation.getId())
                .accommodationName(this.accommodation.getName())
                .hostName(this.accommodation.getHost().getName())
                .guestName(this.guest.getName())
                .price(this.accommodation.getPrice())
                .paymentType(this.paymentType)
                .accommodationType(this.accommodation.getAccommodationType())
                .location(this.accommodation.getLocation())
                .checkInDate(this.checkInDate)
                .checkOutDate(this.checkOutDate)
                .checkInTime(this.accommodation.getCheckInTime())
                .checkOutTime(this.accommodation.getCheckOutTime())
                .guests(this.numberOfGuest)
                .reservationStatus(this.reservationStatus)
                .build();
    }
}
