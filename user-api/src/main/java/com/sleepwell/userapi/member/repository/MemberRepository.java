package com.sleepwell.userapi.member.repository;

import com.sleepwell.userapi.member.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    public Member findById(Long guestId) {
        return new Member();
    }

}
