package com.coincommunity.api.security.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import com.coincommunity.api.security.domain.dto.SignForm;
import com.coincommunity.api.security.exception.DuplicatedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    void duplicateEmail() {
        memberService.signUp(SignForm.of("test@test.com", "abcde123!", "tester1"));
        assertThrows(DuplicatedException.class,
            () -> memberService.signUp(SignForm.of("test@test.com", "abcde123!", "tester2")));
    }

    @Test
    void duplicateNickname() {
        memberService.signUp(SignForm.of("test123@test.com", "abcde123!", "tester"));
        assertThrows(DuplicatedException.class,
            () -> memberService.signUp(SignForm.of("test2123@test.com", "abcde123!", "tester")));
    }

}