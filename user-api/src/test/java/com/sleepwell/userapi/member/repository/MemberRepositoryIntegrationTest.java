package com.sleepwell.userapi.member.repository;

import com.sleepwell.userapi.config.TestConfig;
import com.sleepwell.userapi.member.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@Import(TestConfig.class)
@Transactional(value = "jpaTransactionManager")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MemberRepositoryIntegrationTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setup() {
        //given
        member = memberRepository.save(new Member("name", "email@email.com", "password"));
    }

    @DisplayName("이메일이")
    @Nested
    class FindMemberByEmail {

        @DisplayName("일치하면, 회원 정보 반환")
        @Test
        void withDifferentEmail() {
            //when
            Optional<Member> result = memberRepository.findByEmail("another@email.com");

            //then
            assertTrue(result.isEmpty());
        }

        @DisplayName("일치하지 않으면, 빈 Optional 반환")
        @Test
        void withEqualEmail() {
            //when
            Optional<Member> result = memberRepository.findByEmail(member.getEmail());

            //then
            assertTrue(result.isPresent());
            assertEquals(member, result.get());
        }
    }

    @DisplayName("중복된 이메일이")
    @Nested
    class ExistsMemberByEmail {

        @DisplayName("존재하면, true 반환")
        @Test
        void withExistsEmail() {
            //when
            boolean result = memberRepository.existsByEmail(member.getEmail());

            //then
            assertTrue(result);
        }

        @DisplayName("존재하지 않으면, false 반환")
        @Test
        void withNotExistsEmail() {
            //when
            boolean result = memberRepository.existsByEmail("another@email.com");

            //then
            assertFalse(result);
        }
    }
}
