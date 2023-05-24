package com.sleepwell.userapi.reservation.service;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.service.AccommodationService;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.member.service.MemberService;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.entity.ReservationStatus;
import com.sleepwell.userapi.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReservationService {

    private final MemberService memberService;
    private final AccommodationService accommodationService;
    private final ReservationRepository reservationRepository;

    private static final int PAYMENT_GRACE_PERIOD = 7;

    public Reservation createReservation(Reservation reservation, Long accommodationId, Long guestId) {
        Accommodation accommodation = accommodationService.getAccommodation(accommodationId);
        Member guest = memberService.getMember(guestId);

        if (reservationRepository.existsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(accommodationId, reservation.getCheckInDate(), reservation.getCheckOutDate())) {
            throw new RuntimeException("해당 일자는 예약이 불가합니다.");
        }
        if (accommodation.getMaximumNumberOfGuest() < reservation.getNumberOfGuest()) {
            throw new RuntimeException("최대 숙박 가능 인원을 초과하였습니다.");
        }

        reservation.updateReservation(guest, accommodation);
        reservation.setReservationStatus(ReservationStatus.BEFORE_PAYED);
        return reservationRepository.save(reservation);
    }

    public Reservation getReservation(Long reservationId) {
        Optional<Reservation> findReservation = reservationRepository.findById(reservationId);

        return findReservation.orElseThrow(() -> new RuntimeException("존재하지 않는 예약 정보입니다."));
    }

    /**
     * 특정 기간동안 지불이 완료되지 않은 숙소는 제거
     * 예약 정보는 보존하고, 예약 상태, 숙소 연관관계 삭제
     */
    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Seoul")
    public void cancelNotPayedReservations() {
        log.info("{} - 미지불 고객 예약 취소 시작", LocalDateTime.now());
        List<Reservation> reservations = reservationRepository.findByReservationStatusAndReservedDateLessThanEqual(ReservationStatus.BEFORE_PAYED, LocalDate.now().minusDays(PAYMENT_GRACE_PERIOD));

        for (Reservation reservation : reservations) {
            reservation.cancelReservation();
        }
        log.info("{} - 총 {}건에 대한 미지불 예약 취소", LocalDateTime.now(), reservations.size());
    }
}
