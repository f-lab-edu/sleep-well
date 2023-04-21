package com.sleepwell.userapi.accommodation.repository;

import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationRepository {

    Optional<Accommodation> findById(Long accommodationId);

    //동적쿼리 사용 예정
    List<Accommodation> findAllByAccommodationSearchDto(AccommodationSearchDto accommodationSearchDto);
}
