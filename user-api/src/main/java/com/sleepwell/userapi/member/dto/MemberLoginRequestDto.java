package com.sleepwell.userapi.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class MemberLoginRequestDto {

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

}
