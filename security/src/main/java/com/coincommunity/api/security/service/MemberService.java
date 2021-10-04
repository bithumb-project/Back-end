package com.coincommunity.api.security.service;

import com.coincommunity.api.security.domain.Member;
import com.coincommunity.api.security.domain.Role;
import com.coincommunity.api.security.domain.dto.MemberResponseDto;
import com.coincommunity.api.security.domain.dto.SignForm;
import com.coincommunity.api.security.domain.dto.SignInForm;
import com.coincommunity.api.security.exception.DuplicatedException;
import com.coincommunity.api.security.exception.SecurityRuntimeException;
import com.coincommunity.api.security.provider.SecurityProvider;
import com.coincommunity.api.security.repository.MemberRepository;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final SecurityProvider provider;

    @Transactional
    public void signUp(final SignForm request) throws DuplicatedException {
        log.info("======= Sign-Up Request =======");
        verifyDuplicateEmail(request);
        verifyDuplicateNickname(request);
        request.encodePassword(passwordEncoder);
        memberRepository.save(Member.of(request, makeDefaultRole()));
        log.info("======= Sign-Up Successful!! =======");
        log.info("member's email={}", request.getEmail());
    }

    public MemberResponseDto signIn(final SignInForm request) {
        try {
            Member findMember = memberRepository.findByEmail(request.getEmail()).get();
            String token = (passwordEncoder.matches(
                request.getPassword(),
                findMember.getPassword()))
                ? provider.createToken(request.getEmail(), findMember.getRoles())
                : "잘못된 패스워드입니다.";

            return MemberResponseDto.of(findMember.getId(), findMember.getEmail(), findMember.getNickname(), token, findMember.getRoles());
        } catch (Exception e) {
            throw new SecurityRuntimeException("유효하지 않은 아이디 / 비밀번호", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    private void verifyDuplicateEmail(final SignForm request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            log.error("This Email is Duplicated!!");
            throw new DuplicatedException("This Email is Duplicated!!");
        }
    }

    private void verifyDuplicateNickname(final SignForm request) {
        if (memberRepository.existsByNickname(request.getNickname())) {
            log.error("This Nickname is Duplicated!!");
            throw new DuplicatedException("This Nickname is Duplicated!!");
        }
    }

    private List<Role> makeDefaultRole() {
        return Arrays.asList(Role.USER);
    }

}
