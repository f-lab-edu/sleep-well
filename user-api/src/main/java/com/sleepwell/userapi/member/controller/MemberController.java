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

    /**
     * TODO:
     * 그럼 DTO가 여러 엔티티의 정보를 담고있다면 어떻할까?
     * DTO는 dto.fromEntity처럼 변환하는게 이후 여러 entity를 주입해야할 경우가 생길 수 있어 좋을 것 같다.
     */
    @PostMapping("/create")
    public MemberCreateResponseDto createMember(@RequestBody MemberCreateRequestDto memberCreateRequestDto) {
        Member member = memberCreateRequestDto.toMember();
        Member createdMember = memberService.createMember(member);
        return MemberCreateResponseDto.toDto(createdMember);
    }
}
