package com.sleepwell.userapi.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginRequestDto {

    private final String email;

    private final String password;

}
