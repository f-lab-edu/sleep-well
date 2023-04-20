package com.sleepwell.userapi.reservation.repository;

import java.time.LocalDate;

public interface ReservationRepository {

    boolean exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate);

}
