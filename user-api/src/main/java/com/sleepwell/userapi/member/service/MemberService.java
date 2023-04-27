package com.sleepwell.userapi.member.service;

import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    public Member createMember(Member member) {
        if (memberRepository.existsByEmail(member.getEmail())) {
            throw new RuntimeException("중복된 이메일입니다. 다시 입력해주세요.");
        }

        return memberRepository.save(member);
    }
}
