package com.sleepwell.userapi.accommodation.repository;

import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AccommodationRepository {

    public Optional<Accommodation> findById(Long accommodationId) {
        return Optional.of(Accommodation.builder()
                .accommodationName("숙소 이름")
                .price(1_000_000)
                .accommodationType("숙소 타입")
                .location("지역")
                .checkInDate("체크인 날짜")
                .checkOutDate("체크 아웃 날짜")
                .checkInTime("체크인 시간")
                .checkOutTime("체크아웃 시간")
                .maximumNumberOfGuest(10)
                .description("상세 정보")
                .build());
    }

    //동적쿼리 사용 예정
    public List<Accommodation> findAllByAccommodationSearchDto(AccommodationSearchDto accommodationSearchDto) {
        return List.of(Accommodation.builder()
                .accommodationName("숙소 이름")
                .price(1_000_000)
                .accommodationType("숙소 타입")
                .location("지역")
                .checkInDate("체크인 날짜")
                .checkOutDate("체크 아웃 날짜")
                .checkInTime("체크인 시간")
                .checkOutTime("체크아웃 시간")
                .maximumNumberOfGuest(10)
                .description("상세 정보")
                .build());
    }
}
