package com.sleepwell.userapi.member.dto;

import com.sleepwell.userapi.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@AllArgsConstructor
public class MemberCreateRequestDto {

    @NotBlank
    private final String name;

    @NotBlank
    @Email(message = "유효하지 않은 이메일 형식입니다.", regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
    private final String email;

    @NotBlank
    @Pattern(message = "비밀번호는 8~16자 영문 대/소문자, 숫자, 특수문자를 사용하세요.", regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}")
    private final String password;

    public Member toMember() {
        return new Member(this.name, this.email, this.password);
    }
}
