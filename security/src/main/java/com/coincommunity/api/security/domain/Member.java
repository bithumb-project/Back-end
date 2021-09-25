package com.coincommunity.api.security.domain;

import com.coincommunity.api.security.domain.dto.SignForm;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    private String profile;

    @ElementCollection(fetch = FetchType.LAZY)
    List<Role> roles = new ArrayList<>();

    /**
     * 초기 회원가입시에는 프사를 받지 않는 것으로 일단 구현
     */
    private Member(final String email, final String password, final String nickname, final List<Role> roles) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.roles = roles;
    }

    public static Member of(final SignForm request, final List<Role> roles) {
        return new Member(request.getEmail(), request.getPassword(), request.getNickname(), roles);
    }

}
