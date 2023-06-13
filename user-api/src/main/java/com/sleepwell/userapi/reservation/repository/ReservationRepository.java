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
            "and (" +
                "(r.checkInDate <= :checkInDate and :checkInDate < r.checkOutDate) " +
                "or (r.checkInDate < :checkOutDate and :checkOutDate <= r.checkOutDate)" +
            ")")
    boolean existsReservationInAccommodationThatDay(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate);

    List<Reservation> findByReservationStatusAndReservedDateGreaterThanEqual(ReservationStatus reservationStatus, LocalDate localDate);

}
