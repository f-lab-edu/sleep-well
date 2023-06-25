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
                        streetAddressEq(accommodationSearchDto.getStreetAddress()),
                        detailAddressEq(accommodationSearchDto.getDetailAddress()),
                        postcodeEq(accommodationSearchDto.getPostcode()),
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

    private BooleanExpression streetAddressEq(String streetAddress) {
        return streetAddress != null ? accommodation.address.streetAddress.eq(streetAddress) : null;
    }

    private BooleanExpression detailAddressEq(String detailAddress) {
        return detailAddress != null ? accommodation.address.detailAddress.eq(detailAddress) : null;
    }

    private BooleanExpression postcodeEq(String postcode) {
        return postcode != null ? accommodation.address.postcode.eq(postcode) : null;
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
        return minPrice == null && maxPrice == null ? null : accommodation.price.between(minPrice, maxPrice);
    }

    private BooleanExpression numberOfGuestGoe(Integer numberOfGuest) {
        return numberOfGuest != null ? accommodation.maximumNumberOfGuest.goe(numberOfGuest) : null;
    }
}
