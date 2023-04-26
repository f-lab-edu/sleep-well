package com.sleepwell.userapi.member.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCreateRequestDto {

    private final String name;

    private final String email;

    private final String password;

}
