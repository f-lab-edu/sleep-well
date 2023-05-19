package com.sleepwell.userapi.reservation.repository;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.entity.ReservationStatus;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class ReservationRepository {

    public Optional<Reservation> findById(Long reservationId) {
        return Optional.of(new Reservation(LocalDate.of(2023, 4, 24), LocalDate.of(2023, 4, 24), 10, 100));
    }

    public boolean exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        return false;
    }

    public Reservation save(Reservation reservation) {
        return new Reservation(LocalDate.of(2023, 4, 24), LocalDate.of(2023, 4, 24), 10, 100);
    }

    public List<Reservation> findByReservationStatusAndReservedDateLessThanEqual(ReservationStatus reservationStatus, LocalDate localDate) {
        Reservation reservation = new Reservation(LocalDate.of(2023, 4, 24), LocalDate.of(2023, 4, 24), 10, 100);
        reservation.updateReservation(new Member("이름", "email@email.com", "password"),
                new Accommodation("숙소 이름", 1_000_000, "숙소 타입", "지역", LocalDate.of(2023, 4, 24), LocalDate.of(2023, 4, 25), LocalTime.of(15, 0), LocalTime.of(11, 0), 10, "상세 정보"));
        return List.of(reservation);
    }
}
