package com.sleepwell.userapi.member.dto;

import com.sleepwell.userapi.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberCreateRequestDto {

    private final String name;

    private final String email;

    private final String password;

    public Member toMember() {
        return new Member(this.name, this.email, this.password);
    }
}
