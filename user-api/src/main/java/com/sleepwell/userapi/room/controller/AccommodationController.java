package com.sleepwell.userapi.room.controller;

import com.sleepwell.userapi.room.dto.AccommodationDetailInfoDto;
import com.sleepwell.userapi.room.dto.AccommodationInfoDto;
import com.sleepwell.userapi.room.dto.AccommodationSearchDto;
import com.sleepwell.userapi.room.service.AccommodationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    /**
     * 원하는 방 목록 조회 기능
     */
    @GetMapping
    public List<AccommodationInfoDto> searchRooms(@RequestParam AccommodationSearchDto accommodationSearchDto) {
        return accommodationService.findRooms(accommodationSearchDto);
    }

    /**
     * 방 상세 정보 조회 기능
     */
    @GetMapping("/{accommodationId}")
    public AccommodationDetailInfoDto getRoom(@PathVariable(name = "accommodationId") Long accommodationId) {
        return accommodationService.findRoom(accommodationId);
    }
}
