package com.sleepwell.userapi.member.repository;

import com.sleepwell.userapi.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member save(Member member);

    public Optional<Member> findById(Long guestId);

    public Optional<Member> findByEmail(String email);

    public boolean existsByEmail(String email);
}
