package com.sleepwell.userapi.reservation.service;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.service.AccommodationService;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.member.repository.MemberRepository;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final MemberRepository memberRepository;
    private final AccommodationService accommodationService;
    private final ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation, Long accommodationId, Long guestId) {
        Accommodation accommodation = accommodationService.getAccommodation(accommodationId);
        Member guest = memberRepository.findById(guestId);

        checkIsValidReservationDate(accommodationId, reservation.getCheckInDate(), reservation.getCheckOutDate());
        checkIsValidNumberOfGuest(reservation, accommodation);

        reservation.createReservation(guest, accommodation);
        return reservationRepository.save(reservation);
    }

    public Reservation getReservation(Long reservationId) {
        Optional<Reservation> findReservation = reservationRepository.findById(reservationId);

        return findReservation.orElseThrow(() -> new RuntimeException("존재하지 않는 예약 정보입니다."));
    }

    private void checkIsValidReservationDate(Long accommodationId, LocalDate checkInDate, LocalDate checkOutDate) {
        if (reservationRepository.exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(accommodationId, checkInDate, checkOutDate)) {
            throw new RuntimeException("해당 일자는 예약이 불가합니다.");
        }
    }

    private static void checkIsValidNumberOfGuest(Reservation reservation, Accommodation accommodation) {
        if (accommodation.getMaximumNumberOfGuest() < reservation.getNumberOfGuest()) {
            throw new RuntimeException("최대 숙박 가능 인원을 초과하였습니다.");
        }
    }
}
