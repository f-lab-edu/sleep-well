package com.sleepwell.userapi.member.repository;

import com.sleepwell.userapi.member.entity.Member;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MemberRepository {

    public Member save(Member member) {
        member.setId(1L);
        return member;
    }

    public Member findById(Long guestId) {
        return new Member("이름", "eamil@email.com", "{bcrypt}$2a$10$N5t/NQnCqg9ONzUrJTa06Ol7jEBOZDi07ZLlQMFJLCfo6uHs4Fjr.");
    }

    public Optional<Member> findByEmail(String email) {
        return Optional.of(new Member("이름", "eamil@email.com", "{bcrypt}$2a$10$N5t/NQnCqg9ONzUrJTa06Ol7jEBOZDi07ZLlQMFJLCfo6uHs4Fjr."));
    }

    public boolean existsByEmail(String email) {
        return false;
    }
}
