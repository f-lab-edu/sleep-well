package com.sleepwell.userapi.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberCreateResponseDto {

    private final String name;

    private final String email;

}
