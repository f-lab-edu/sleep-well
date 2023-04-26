package com.sleepwell.userapi.accommodation.repository;

import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AccommodationRepository {

    public Optional<Accommodation> findById(Long accommodationId) {
        return Optional.of(new Accommodation("숙소 이름", 1_000_000, "숙소 타입", "지역", LocalDate.of(2023, 4, 24), LocalDate.of(2023, 4, 25), LocalTime.of(15, 0), LocalTime.of(11, 0), 10, "상세 정보"));
    }

    //동적쿼리 사용 예정
    public List<Accommodation> findAllByAccommodationSearchDto(AccommodationSearchDto accommodationSearchDto) {
        return List.of(new Accommodation("숙소 이름", 1_000_000, "숙소 타입", "지역", LocalDate.of(2023, 4, 24), LocalDate.of(2023, 4, 25), LocalTime.of(15, 0), LocalTime.of(11, 0), 10, "상세 정보"));
    }
}
