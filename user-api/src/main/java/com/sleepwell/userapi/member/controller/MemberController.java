package com.sleepwell.userapi.member.controller;

import com.sleepwell.userapi.member.dto.MemberCreateRequestDto;
import com.sleepwell.userapi.member.dto.MemberCreateResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/member")
public class MemberController {

    //TODO: MemberService 구현

    @PostMapping("/create")
    public MemberCreateResponseDto createMember(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        //entity = MemberService.createMember
        return MemberCreateResponseDto.builder()
                .name("회원 이름")
                .email("email@email.com")
                .build();
    }
}
