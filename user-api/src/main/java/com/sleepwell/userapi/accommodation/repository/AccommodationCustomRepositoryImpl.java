package com.sleepwell.userapi.accommodation.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sleepwell.userapi.accommodation.dto.AccommodationSearchDto;
import com.sleepwell.userapi.accommodation.entity.Accommodation;
import com.sleepwell.userapi.accommodation.entity.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static com.sleepwell.userapi.accommodation.entity.QAccommodation.accommodation;
import static com.sleepwell.userapi.accommodation.entity.QAddress.address;

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
                        addressEq(accommodationSearchDto.getStreetAddress(), accommodationSearchDto.getDetailAddress(), accommodationSearchDto.getPostcode()),
                        notExistsReservationBetweenDates(accommodationSearchDto.getCheckInDate(), accommodationSearchDto.getCheckOutDate()),
                        priceBetween(accommodationSearchDto.getMinPrice(), accommodationSearchDto.getMaxPrice()),
                        numberOfGuestGoe(accommodationSearchDto.getNumberOfGuest()))
                .distinct()
                .fetch();
    }

    private BooleanExpression nameEq(String accommodationName) {
        return accommodationName != null ? accommodation.accommodationName.eq(accommodationName) : null;
    }

    private BooleanExpression typeEq(String accommodationType) {
        return accommodationType != null ? accommodation.accommodationType.eq(accommodationType) : null;
    }

    private BooleanExpression addressEq(String streetAddress, String detailAddress, String postcode) {
        return address.streetAddress.eq(streetAddress)
                .and(address.detailAddress.eq(detailAddress))
                .and(address.postcode.eq(postcode));
    }

    private BooleanExpression addressEq(Address address) {
        return address != null ? accommodation.address.eq(address) : null;
    }

    private BooleanExpression notExistsReservationBetweenDates(LocalDate checkInDate, LocalDate checkOutDate) {
        if (checkInDate == null && checkOutDate == null) {
            return null;
        } else if (checkInDate == null) {
            checkInDate = checkOutDate.minusDays(1);
        } else if (checkOutDate == null) {
            checkOutDate = checkInDate.plusDays(1);
        }

        return accommodation.reservations.any().checkInDate.notBetween(checkInDate, checkOutDate.minusDays(1))
                .and(accommodation.reservations.any().checkOutDate.notBetween(checkInDate.plusDays(1), checkOutDate));
    }

    private BooleanExpression priceBetween(Integer minPrice, Integer maxPrice) {
        if (minPrice == null && maxPrice == null) {
            return null;
        } else if (minPrice == null) {
            return accommodation.price.loe(maxPrice);
        } else if (maxPrice == null) {
            return accommodation.price.goe(minPrice);
        }

        return accommodation.price.between(minPrice, maxPrice);
    }

    private BooleanExpression numberOfGuestGoe(Integer numberOfGuest) {
        return numberOfGuest != null ? accommodation.maximumNumberOfGuest.goe(numberOfGuest) : null;
    }
}
