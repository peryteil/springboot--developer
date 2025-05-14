package me.shinsunyoung.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "provider")
    private String provider; // google, naver, kakao

    @Column(name = "provider_id")
    private String providerId;

    @Builder
    public User(String email, String password, String nickname, String provider, String providerId) {
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.provider = provider;
        this.providerId = providerId;
    }

    // 소셜 로그인 전용 정적 팩토리 메서드
    public static User socialUser(String email, String nickname, String provider, String providerId) {
        return User.builder()
                .email(email)
                .nickname(nickname)
                .password(null)
                .provider(provider)
                .providerId(providerId)
                .build();
    }

    public User update(String nickname) {
        this.nickname = nickname;
        return this;
    }

    // Spring Security UserDetails 구현

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
