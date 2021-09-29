package com.coincommunity.api.security.domain.dto;

import com.coincommunity.api.security.domain.Role;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Getter
@Component
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberResponseDto {

    private Long id;
    private String email;
    private String nickname;
    private String token;
    private List<Role> roles;

    private MemberResponseDto(final Long id, final String email, final String nickname, final String token, final List<Role> roles) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
        this.token = token;
        this.roles = roles;
    }

    public static MemberResponseDto of(final Long id, final String email, final String nickname, final String token, final List<Role> roles) {
        return new MemberResponseDto(id, email, nickname, token, roles);
    }

}
