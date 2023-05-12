package com.sleepwell.userapi.reservation.service;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.service.AccommodationService;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.member.repository.MemberRepository;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.repository.ReservationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private AccommodationService accommodationService;

    @InjectMocks
    private ReservationService reservationService;

    private Member member;

    private Accommodation accommodation;

    private Reservation reservation;

    @BeforeEach
    void setup() {
        member = mock(Member.class);

        accommodation = new Accommodation("숙소 이름", 1_000_000, "숙소 타입", "지역", LocalDate.of(2023, 4, 24), LocalDate.of(2023, 4, 25), LocalTime.of(15, 0), LocalTime.of(11, 0), 10, "상세 정보");

        reservation = new Reservation("지불 타입", LocalDate.of(2023, 4, 24), LocalDate.of(2023, 4, 24), 10, 100);
    }

    @DisplayName("예약 일자 사이에 이미 예약이 있다면 예약 불가")
    @Test
    void createReservationWithInvalidCheckInDate() {
        //given
        when(reservationRepository.exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(any(), any(), any())).thenReturn(true);
        when(accommodationService.getAccommodation(any())).thenReturn(accommodation);
        when(memberRepository.findById(any())).thenReturn(member);

        //then
        assertThrows(RuntimeException.class, () -> reservationService.createReservation(reservation, 1L, 1L));
    }

    @DisplayName("최대 숙박 인원을 초과하면 예약 불가")
    @Test
    void createReservationWithInvalidNumberOfGuest() {
        //given
        when(reservationRepository.exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(any(), any(), any())).thenReturn(false);
        when(accommodationService.getAccommodation(any())).thenReturn(accommodation);
        when(memberRepository.findById(any())).thenReturn(member);

        //when
        reservation.setNumberOfGuest(accommodation.getMaximumNumberOfGuest() + 1);

        //then
        assertThrows(RuntimeException.class, () -> reservationService.createReservation(reservation, 1L, 1L));
    }

    @DisplayName("정상 예약 생성 요청 시 예약 정보 반환")
    @Test
    void createReservationWithValidCheckInDate() {
        //given
        when(reservationRepository.exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(any(), any(), any())).thenReturn(false);
        when(reservationRepository.save(any())).thenReturn(reservation);
        when(accommodationService.getAccommodation(any())).thenReturn(accommodation);
        when(memberRepository.findById(any())).thenReturn(member);

        //then
        assertEquals(reservationService.createReservation(reservation, 1L, 1L), reservation);
    }

    @DisplayName("존재하지 않는 예약 정보 조회 시 예외 발생")
    @Test
    void getReservationWithInValidReservationId() {
        //given
        when(reservationRepository.findById(any())).thenReturn(Optional.empty());

        //then
        assertThrows(RuntimeException.class, () -> reservationService.getReservation(1L));
    }

    @DisplayName("정상 조회 요청 시 예약 정보 반환")
    @Test
    void getReservationWithValidReservationId() {
        //given
        when(reservationRepository.findById(any())).thenReturn(Optional.of(reservation));

        //then
        assertEquals(reservation, reservationService.getReservation(1L));
    }

}
