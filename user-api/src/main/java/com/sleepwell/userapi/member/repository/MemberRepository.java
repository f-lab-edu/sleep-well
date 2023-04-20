package com.sleepwell.userapi.member.repository;

import com.sleepwell.userapi.member.entity.Member;

public interface MemberRepository {

    Member findById(Long guestId);

}
