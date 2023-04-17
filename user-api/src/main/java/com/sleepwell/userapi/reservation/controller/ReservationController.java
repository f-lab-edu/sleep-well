package com.sleepwell.userapi.reservation.controller;

import com.sleepwell.userapi.reservation.dto.ReservationDetailInfoDto;
import com.sleepwell.userapi.reservation.dto.ReservationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservation")
@RequiredArgsConstructor
public class ReservationController {

    /**
     * 숙소 예약 기능
     */
    @PostMapping
    public ReservationDetailInfoDto createReservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        // entity = reservationRequestDto.toEntity();
        // reservationService.createReservation(entity);
        return ReservationDetailInfoDto.builder()
                .reservationId(1L)
                .accommodationId(1L)
                .accommodationName("숙소 이름")
                .type("아파트")
                .checkInDate("2023-08-01")
                .checkOutDate("2023-08-04")
                .guests(4)
                .build();
    }

    /**
     * 단일 예약 정보 조회 기능
     * 숙소 예약 성공 시, 해당 url 로 redirect
     */
    @GetMapping("/{reservationId}")
    public ReservationDetailInfoDto getReservation(@PathVariable(name = "reservationId") Long reservationId) {
        // entity = reservationService.getReservation(reservationId);
        return ReservationDetailInfoDto.builder()
                .reservationId(reservationId)
                .accommodationId(1L)
                .accommodationName("숙소 이름")
                .type("아파트")
                .checkInDate("2023-08-01")
                .checkOutDate("2023-08-04")
                .guests(4)
                .build();
    }
}
