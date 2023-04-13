package com.sleepwell.userapi.reservation.controller;

import com.sleepwell.userapi.reservation.dto.ReservationDetailInfoDto;
import com.sleepwell.userapi.reservation.dto.ReservationRequestDto;
import com.sleepwell.userapi.reservation.service.ReservationService;
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

    private final ReservationService reservationService;

    /**
     * 숙소 예약 기능
     */
    @PostMapping
    public ReservationDetailInfoDto reservation(@RequestBody ReservationRequestDto reservationRequestDto) {
        return reservationService.createReservation(reservationRequestDto);
    }

    /**
     * 단일 예약 정보 조회 기능
     * 숙소 예약 성공 시, 해당 url 로 redirect
     */
    @GetMapping("/{reservationId}")
    public ReservationDetailInfoDto getReservation(@PathVariable(name = "reservationId") Long reservationId) {
        return reservationService.getReservation(reservationId);
    }
}
