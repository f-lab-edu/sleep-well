package com.sleepwell.userapi.member.repository;

import com.sleepwell.userapi.member.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    public Member findById(Long guestId) {
        return new Member("이름", "eamil@email.com", "password");
    }

    public boolean existsByEmail(String email) {
        return false;
    }

    public Member save(Member member) {
        member.setId(1L);
        return member;
    }
}
