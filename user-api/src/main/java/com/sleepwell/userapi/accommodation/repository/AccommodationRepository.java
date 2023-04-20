package com.sleepwell.userapi.accommodation.repository;

import com.sleepwell.userapi.accommodation.entity.Accommodation;

public interface AccommodationRepository {

    Accommodation findById(Long accommodationId);

}
