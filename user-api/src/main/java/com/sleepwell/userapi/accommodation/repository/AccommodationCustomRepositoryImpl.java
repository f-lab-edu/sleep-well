package com.sleepwell.userapi.accommodation.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.sleepwell.userapi.accommodation.entity.QAccommodation.accommodation;

@Repository
@RequiredArgsConstructor
public class AccommodationCustomRepositoryImpl implements AccommodationCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Accommodation> findAllByAccommodationSearchDto(AccommodationSearchDto accommodationSearchDto) {
        return jpaQueryFactory
                .selectFrom(accommodation)
                .where(nameEq(accommodationSearchDto.getAccommodationName()),
                        typeEq(accommodationSearchDto.getAccommodationType()),
                        locationEq(accommodationSearchDto.getLocation()),
                        notExistsReservationBetweenDates(accommodationSearchDto.getCheckInDate(), accommodationSearchDto.getCheckOutDate()),
                        priceBetween(accommodationSearchDto.getMinPrice(), accommodationSearchDto.getMaxPrice()),
                        numberOfGuestLoe(accommodationSearchDto.getNumberOfGuest()))
                .fetch();
    }

    private BooleanExpression nameEq(String accommodationName) {
        return accommodationName != null ? accommodation.accommodationName.eq(accommodationName) : null;
    }

    private BooleanExpression typeEq(String accommodationType) {
        return accommodationType != null ? accommodation.accommodationType.eq(accommodationType) : null;
    }

    private BooleanExpression locationEq(String location) {
        return location != null ? accommodation.location.eq(location) : null;
    }

    // 예약들 중 이런 정보가 있으면 탈락
    private BooleanExpression notExistsReservationBetweenDates(LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkInDate == null && checkOutDate == null) {
            return null;
        } else if (checkInDate == null) {
            checkInDate = checkOutDate.minusDays(1);
        } else if (checkOutDate == null) {
            checkOutDate = checkInDate.plusDays(1);
        }

        return accommodation.reservations.any().checkInDate.notBetween(checkInDate, checkOutDate)
                .and(accommodation.reservations.any().checkOutDate.notBetween(checkInDate, checkOutDate));
    }

    private BooleanExpression priceBetween(Integer minPrice, Integer maxPrice) {
        return (minPrice != null && maxPrice != null) ? accommodation.price.between(minPrice, maxPrice) : null;
    }

    private BooleanExpression numberOfGuestLoe(Integer numberOfGuest) {
        return numberOfGuest != null ? accommodation.maximumNumberOfGuest.loe(numberOfGuest) : null;
    }


}
