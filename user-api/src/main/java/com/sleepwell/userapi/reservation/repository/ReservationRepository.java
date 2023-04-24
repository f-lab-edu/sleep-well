package com.sleepwell.userapi.reservation.repository;

import com.sleepwell.userapi.reservation.entity.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class ReservationRepository {

    public Optional<Reservation> findById(Long reservationId) {
        return Optional.of(Reservation.builder()
                .paymentType("지불 타입")
                .checkInDate(LocalDate.of(2023, 4, 24))
                .checkOutDate(LocalDate.of(2023, 4, 24))
                .numberOfGuest(10)
                .build());
    }

    public boolean exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        return true;
    }

    public Reservation save(Reservation reservation) {
        return Reservation.builder()
                .paymentType("지불 타입")
                .checkInDate(LocalDate.of(2023, 4, 24))
                .checkOutDate(LocalDate.of(2023, 4, 24))
                .numberOfGuest(10)
                .build();
    }
}
