package com.sleepwell.userapi.accommodation.dto;

import com.sleepwell.userapi.accommodation.entity.Accommodation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Getter
@AllArgsConstructor
public class AccommodationCreateRequestDto {

    //TODO: securityContextHolder 에 저장된 정보를 사용하도록 변경 예정
    @NotNull
    private Long memberId;

    @NotBlank
    private String accommodationName;

    @Min(1)
    private int price;

    @NotBlank
    private String accommodationType;

    @NotBlank
    private String location;

    @NotNull
    @DateTimeFormat(pattern = "kk:mm")
    private LocalTime checkInTime;

    @NotNull
    @DateTimeFormat(pattern = "kk:mm")
    private LocalTime checkOutTime;

    @Min(1)
    private int maximumNumberOfGuest;

    private String description;

    public Accommodation toEntity() {
        return new Accommodation(accommodationName, price, accommodationType, location, checkInTime, checkOutTime, maximumNumberOfGuest, description);
    }
}
