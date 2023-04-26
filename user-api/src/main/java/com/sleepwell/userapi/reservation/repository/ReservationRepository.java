package com.sleepwell.userapi.reservation.repository;

import com.sleepwell.userapi.reservation.entity.Reservation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class ReservationRepository {

    public Optional<Reservation> findById(Long reservationId) {
        return Optional.of(new Reservation("지불 타입", LocalDate.of(2023, 4, 24), LocalDate.of(2023, 4, 24), 10));
    }

    public boolean exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        return false;
    }

    public Reservation save(Reservation reservation) {
        return new Reservation("지불 타입", LocalDate.of(2023, 4, 24), LocalDate.of(2023, 4, 24), 10);
    }
}
