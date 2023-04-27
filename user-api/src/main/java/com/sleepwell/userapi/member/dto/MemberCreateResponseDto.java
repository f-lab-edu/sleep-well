package com.sleepwell.userapi.member.dto;

import com.sleepwell.userapi.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberCreateResponseDto {

    private final String name;

    private final String email;

    public static MemberCreateResponseDto toDto(Member member) {
        return MemberCreateResponseDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .build();
    }
}
