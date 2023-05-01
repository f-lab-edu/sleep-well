package com.sleepwell.userapi.member.controller;

import com.sleepwell.userapi.member.dto.MemberCreateRequestDto;
import com.sleepwell.userapi.member.dto.MemberCreateResponseDto;
import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/create")
    public MemberCreateResponseDto createMember(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        Member member = memberCreateRequestDto.toMember();
        Member createdMember = memberService.createMember(member);
        return MemberCreateResponseDto.toDto(createdMember);
    }
}
