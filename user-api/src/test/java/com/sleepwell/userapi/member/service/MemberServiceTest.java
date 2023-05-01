package com.sleepwell.userapi.member.service;

import com.sleepwell.userapi.member.entity.Member;
import com.sleepwell.userapi.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {


    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    Member member;

    @BeforeEach
    void setup() {
        member = new Member("이름", "email@email", "password");
    }

    @DisplayName("중복 이메일 입력 시 예외 발생")
    @Test
    void createMemberWithDuplicateEmail() {
        //given
        when(memberRepository.existsByEmail(any())).thenReturn(true);

        //then
        assertThrows(RuntimeException.class, () -> memberService.createMember(member));
    }

    @DisplayName("정상 회원 정보 입력 시 회원 정보 반환")
    @Test
    void createMemberWithValidMemberInfo() {
        //given
        when(memberRepository.existsByEmail(any())).thenReturn(false);
        when(passwordEncoder.encode(any())).thenReturn("1234");
        when(memberRepository.save(any())).thenReturn(member);

        //then
        Member createdMember = memberService.createMember(member);
        assertEquals(member, createdMember);
    }
}
