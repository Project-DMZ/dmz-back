package com.dmz.api.member.repository;

import com.dmz.api.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * packageName    : com.dmz.api.member.repository
 * fileName       : MemberRepository
 * author         : MinKyu Park
 * date           : 2023-12-28
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2023-12-28        MinKyu Park       최초 생성
 */
public interface MemberRepository extends JpaRepository<Member, Long> {
	Optional<Member> findByProviderId(String providerId);
	Optional<Member> findByEmail(String email);
}
