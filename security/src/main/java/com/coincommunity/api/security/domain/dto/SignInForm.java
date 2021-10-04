package com.coincommunity.api.security.domain.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInForm {

    private String email;

    private String password;


    private SignInForm(final String email, final String password) {
        this.email = email;
        this.password = password;
    }

    public static SignInForm of(final String email, final String password) {
        return new SignInForm(email, password);
    }

}
