package com.sleepwell.userapi.reservation.repository;

import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("select count(r) > 0 " +
            "from Reservation r " +
            "where r.accommodation.id = :accommodationId " +
            "and (r.checkInDate between :checkInDate and :checkOutDate " +
            "or r.checkOutDate between :checkInDate and :checkOutDate)")
    boolean existsReservationInAccommodationThatDay(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate);

    List<Reservation> findByReservationStatusAndReservedDateLessThanEqual(ReservationStatus reservationStatus, LocalDate localDate);

}
