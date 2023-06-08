package com.sleepwell.userapi.reservation.service;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.service.AccommodationService;
import com.sleepwell.userapi.error.ErrorStatus;
import com.sleepwell.userapi.error.exception.BaseException;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.member.service.MemberService;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final MemberService memberService;
    private final AccommodationService accommodationService;
    private final ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation, Long accommodationId, Long guestId) {
        Accommodation accommodation = accommodationService.getAccommodation(accommodationId);
        Member guest = memberService.getMember(guestId);

        if (reservationRepository.existsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(accommodationId, reservation.getCheckInDate(), reservation.getCheckOutDate())) {
            throw new BaseException(ErrorStatus.INVALID_RESERVATION_DATE);
        }
        if (accommodation.getMaximumNumberOfGuest() < reservation.getNumberOfGuest()) {
            throw new BaseException(ErrorStatus.INVALID_NUMBER_OF_GUEST);
        }

        reservation.updateReservation(guest, accommodation);
        return reservationRepository.save(reservation);
    }

    public Reservation getReservation(Long reservationId) {
        Optional<Reservation> findReservation = reservationRepository.findById(reservationId);

        return findReservation.orElseThrow(() -> new BaseException(ErrorStatus.RESERVATION_NOT_FOUND));
    }
}
