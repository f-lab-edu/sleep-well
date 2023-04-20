package com.sleepwell.userapi.reservation.service;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.repository.AccommodationRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private AccommodationRepository accommodationRepository;

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Member member;

    private Accommodation accommodation;

    private Reservation reservation;

    @BeforeEach
    void setup() {
        member = new Member();

        accommodation = Accommodation.builder()
                .maximumNumberOfGuest(1000)
                .build();

        reservation = Reservation.builder()
                .paymentType("지불 타입")
                .checkInDate(LocalDate.of(2023, 4, 20))
                .checkOutDate(LocalDate.of(2023, 4, 22))
                .numberOfGuest(1000)
                .build();

        when(memberRepository.findById(any())).thenReturn(member);
    }

    @DisplayName("예약 일자 사이에 이미 예약이 있다면 예약 불가")
    @Test
    void createReservationWithInvalidCheckInDate() {
        //given
        when(reservationRepository.exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(any(), any(), any())).thenReturn(true);
        when(accommodationRepository.findById(any())).thenReturn(accommodation);

        //then
        assertThrows(RuntimeException.class, () -> reservationService.createReservation(reservation, 1L, 1L));
    }

    @DisplayName("최대 숙박 인원을 초과하면 예약 불가")
    @Test
    void createReservationWithInvalidNumberOfGuest() {
        //given
        when(reservationRepository.exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(any(), any(), any())).thenReturn(false);
        when(accommodationRepository.findById(any())).thenReturn(accommodation);

        //when
        reservation.setNumberOfGuest(accommodation.getMaximumNumberOfGuest() + 1);

        //then
        assertThrows(RuntimeException.class, () -> reservationService.createReservation(reservation, 1L, 1L));
    }

    @DisplayName("정상 요청 시 예약 정보 반환")
    @Test
    void createReservationWithValidCheckInDate() {
        //given
        when(reservationRepository.exitsByAccommodationIdAndCheckInDateGreaterThanEqualAndCheckOutDateLessThanEqual(any(), any(), any())).thenReturn(false);
        when(accommodationRepository.findById(any())).thenReturn(accommodation);

        //then
        assertEquals(reservationService.createReservation(reservation, 1L, 1L), reservation);
    }

}