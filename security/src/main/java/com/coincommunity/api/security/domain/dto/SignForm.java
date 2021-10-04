package com.coincommunity.api.security.domain.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignForm {

    @Email
    @NotNull(message = "이메일을 입력해주세요!")
    private String email;

    @NotNull(message = "비밀번호를 입력해주세요!")
    @Pattern(regexp = "^(?=.*[a-zA-Z0-9`~!@#$%^&*()\\-_+=\\\\]).{8,15}$",
        message = "비밀번호는 영문/숫자/특수문자 조합 8자리~15자리여야 합니다!")
    private String password;

    @NotNull(message = "닉네임을 입력해주세요!")
    private String nickname;

    private SignForm(final String email, final String password, final String nickname) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
    }

    public static SignForm of(final String email, final String password, final String nickname) {
        return new SignForm(email, password, nickname);
    }


    public SignForm encodePassword(final PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
        return this;
    }

}
