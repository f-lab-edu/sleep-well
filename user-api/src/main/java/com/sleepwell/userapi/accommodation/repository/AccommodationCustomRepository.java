package com.sleepwell.userapi.accommodation.repository;

import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;

import java.util.List;

public interface AccommodationCustomRepository {

    List<Accommodation> findAllByAccommodationSearchDto(AccommodationSearchDto accommodationSearchDto);

}
