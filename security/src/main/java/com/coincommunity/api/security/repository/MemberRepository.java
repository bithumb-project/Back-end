package com.coincommunity.api.security.repository;

import com.coincommunity.api.security.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmail(final String email);

    boolean existsByNickname(final String nickname);

    Optional<Member> findByEmail(String email);

}
