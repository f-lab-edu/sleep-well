package com.sleepwell.userapi.reservation.repository;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    public Optional<Reservation> findById(Long reservationId);

    public boolean exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate);

    public List<Reservation> findByReservationStatusAndReservedDateLessThanEqual(ReservationStatus reservationStatus, LocalDate localDate);
}
