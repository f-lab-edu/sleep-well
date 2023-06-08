package com.sleepwell.userapi.reservation;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.repository.AccommodationRepository;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.member.repository.MemberRepository;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.entity.ReservationStatus;
import com.sleepwell.userapi.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class ReservationRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private AccommodationRepository accommodationRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @DisplayName("숙박 일자하려는 날짜의")
    @Nested
    class CheckReservationDate {

        private Accommodation accommodation;

        private LocalDate checkInDate;

        private LocalDate checkOutDate;

        @BeforeEach
        void setUp() {
            //given
            checkInDate = LocalDate.of(2023, 6, 8);
            checkOutDate = LocalDate.of(2023, 6, 10);

            Member member = memberRepository.save(new Member("사용자 이름", "email@email.com", "password"));
            Reservation reservation = reservationRepository.save(new Reservation(checkInDate, checkOutDate, LocalDate.of(2023, 6, 8), 1, 1000));
            accommodation = accommodationRepository.save(new Accommodation("숙소 이름", 1000, "HOTEL", "지역", LocalTime.of(11, 0), LocalTime.of(15, 0), 10, "세부사항"));
            reservation.updateReservation(member, accommodation);
        }

        @DisplayName("사이에 체크인 하는 예약이 존재하면 true 반환")
        @Test
        void hasCheckInDateBetweenReservationDate() {
            //when
            boolean result = reservationRepository.existsReservationInAccommodationThatDay(accommodation.getId(), checkInDate.plusDays(1), checkOutDate.plusDays(1));

            //then
            assertTrue(result);
        }

        @DisplayName("사이에 체크아웃 하는 예약이 존재하면 true 반환")
        @Test
        void hasCheckOutDateBetweenReservationDate() {
            //when
            boolean result = reservationRepository.existsReservationInAccommodationThatDay(accommodation.getId(), checkInDate.minusDays(1), checkOutDate.minusDays(1));

            //then
            assertTrue(result);
        }

        @DisplayName("사이에 예약이 존재하지 않으면 false 반환")
        @Test
        void hasNotAnyReservationBetweenReservationDate() {
            //when
            boolean result = reservationRepository.existsReservationInAccommodationThatDay(accommodation.getId(), checkInDate.minusDays(20), checkOutDate.minusDays(20));

            //then
            assertFalse(result);
        }

        @DisplayName("체크인과 같은 날에 체크인하는 예약이 존재하면 true 반환")
        @Test
        void isEqualCheckInDateWithOriginCheckInDate() {
            //when
            boolean result = reservationRepository.existsReservationInAccommodationThatDay(accommodation.getId(), checkInDate, checkOutDate.plusDays(1));

            //then
            assertTrue(result);
        }

        @DisplayName("체크인과 같은 날에 체크아웃하는 예약이 존재하면 false 반환")
        @Test
        void isEqualCheckOutDateWithOriginCheckInDate() {
            //when
            boolean result = reservationRepository.existsReservationInAccommodationThatDay(accommodation.getId(), checkInDate.minusDays(1), checkInDate);

            //then
            assertFalse(result);
        }

        @DisplayName("체크아웃과 같은 날에 체크인하는 예약이 존재하면 false 반환")
        @Test
        void isEqualCheckInDateWithOriginCheckOutDate() {
            //when
            boolean result = reservationRepository.existsReservationInAccommodationThatDay(accommodation.getId(), checkOutDate, checkOutDate.plusDays(1));

            //then
            assertFalse(result);
        }

        @DisplayName("체크아웃과 같은 날에 체크아웃하는 예약이 존재하면 true 반환")
        @Test
        void isEqualCheckOutDateWithOriginCheckOutDate() {
            //when
            boolean result = reservationRepository.existsReservationInAccommodationThatDay(accommodation.getId(), checkInDate.minusDays(1), checkOutDate);

            //then
            assertTrue(result);
        }
    }

    @DisplayName("결제 상태가")
    @Nested
    class FindReservationStatusAndReservedDate {

        private final int numberOfReservation = 7;

        @BeforeEach
        void setup() {
            //given
            for (int i = 0; i < numberOfReservation; i++) {
                Reservation reservation = reservationRepository.save(new Reservation(LocalDate.of(2023, 6, 8), LocalDate.of(2023, 6, 10), LocalDate.of(2023, 6, 8), 1, 1000));
                reservation.setReservationStatus(ReservationStatus.BEFORE_PAYED);
            }
        }

        @DisplayName("일치하지 않는 예약은 조회되지 않는다.")
        @Test
        void withDifferentReservationStatus() {
            //when
            List<Reservation> result = reservationRepository.findByReservationStatusAndReservedDateGreaterThanEqual(ReservationStatus.RESERVED, LocalDate.of(2023, 6, 8));

            //then
            assertTrue(result.isEmpty());
        }

        @DisplayName("일치하지만, 특정 일자 이전의 예약은 조회되지 않는다.")
        @Test
        void withValidReservationStatusAndInValidReservedDate() {
            //when
            List<Reservation> result = reservationRepository.findByReservationStatusAndReservedDateGreaterThanEqual(ReservationStatus.BEFORE_PAYED, LocalDate.of(2023, 6, 10));

            //then
            assertTrue(result.isEmpty());
        }

        @DisplayName("일치하면, 특정 일자 이후의 예약 모두 조회")
        @Test
        void withValidReservationStatusAndValidReservedDate() {
            //when
            List<Reservation> result = reservationRepository.findByReservationStatusAndReservedDateGreaterThanEqual(ReservationStatus.BEFORE_PAYED, LocalDate.of(2023, 6, 8));

            //then
            assertEquals(result.size(), numberOfReservation);
            assertEquals(ReservationStatus.BEFORE_PAYED, result.get(0).getReservationStatus());
            assertTrue(result.get(0).getReservedDate().isAfter(LocalDate.of(2023, 6, 8)) || result.get(0).getReservedDate().isEqual(LocalDate.of(2023, 6, 8)));
        }
    }
}
