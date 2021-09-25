package com.coincommunity.api.security.controller;

import com.coincommunity.api.security.domain.dto.SignForm;
import com.coincommunity.api.security.service.MemberService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> signUp(@Valid @RequestBody final SignForm request) {
        memberService.signUp(request);
        return ResponseEntity.ok().body("sign-up successful");
    }
}
