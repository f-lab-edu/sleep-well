package com.sleepwell.userapi.reservation.controller;

import com.sleepwell.userapi.reservation.dto.ReservationDetailInfoDto;
import com.sleepwell.userapi.reservation.dto.ReservationRequestDto;
import com.sleepwell.userapi.reservation.entity.Reservation;
import com.sleepwell.userapi.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    /**
     * 숙소 예약 기능
     */
    @PostMapping
    public ReservationDetailInfoDto createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        Reservation reservation = reservationRequestDto.toEntity();
        Reservation createdReservation = reservationService.createReservation(reservation, reservationRequestDto.getAccommodationId(), reservationRequestDto.getGuestId());
        return createdReservation.toReservationDetailInfoDto();
    }

    /**
     * 단일 예약 정보 조회 기능
     */
    @GetMapping("/{reservationId}")
    public ReservationDetailInfoDto getReservation(@PathVariable(name = "reservationId") Long reservationId) {
        // entity = reservationService.getReservation(reservationId);
        return ReservationDetailInfoDto.builder()
                .reservationId(reservationId)
                .accommodationId(1L)
                .accommodationName("숙소 이름")
                .hostName("호스트 이름")
                .guestName("예약자 이름")
                .price(1_000_000)
                .paymentType("결제 타입")
                .accommodationType("아파트")
                .location("지역")
                .checkInDate(LocalDate.of(2023, 8, 1))
                .checkOutDate(LocalDate.of(2023, 8, 4))
                .checkInTime("15:00")
                .checkOutTime("13:00")
                .guests(4)
                .reservationStatus("예약 상태")
                .build();
    }
}
