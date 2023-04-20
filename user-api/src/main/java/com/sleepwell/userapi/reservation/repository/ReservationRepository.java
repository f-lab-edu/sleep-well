package com.sleepwell.userapi.reservation.repository;

import com.sleepwell.userapi.reservation.entity.Reservation;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservationRepository {

    Optional<Reservation> findById(Long reservationId);

    boolean exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate);
}
