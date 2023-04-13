package com.sleepwell.userapi.room.service;

import com.sleepwell.userapi.room.dto.AccommodationDetailInfoDto;
import com.sleepwell.userapi.room.dto.AccommodationInfoDto;
import com.sleepwell.userapi.room.dto.AccommodationSearchDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationService {
    public List<AccommodationInfoDto> findRooms(AccommodationSearchDto accommodationSearchDto) {
        //repository.findBy~~
        return List.of(AccommodationInfoDto.builder()
                .accommodationName("숙소 이름")
                .location("지역")
                .type("숙소 타입")
                .build());
    }

    public AccommodationDetailInfoDto findRoom(Long accommodationId) {
        //repository.findById
        Optional<AccommodationDetailInfoDto> accommodationInfo = Optional.ofNullable(AccommodationDetailInfoDto.builder()
                .accommodationId(accommodationId)
                .accommodationName("숙소 이름")
                .type("숙소 타입")
                .location("지역")
                .checkInDate("2023-08-01")
                .checkOutDate("2023-08-01")
                .guests(5)
                .build());

        //
        if (accommodationInfo.isEmpty()) {
            throw new RuntimeException("존재하지 않는 숙소입니다.");
        }
        return accommodationInfo.get();
    }
}
