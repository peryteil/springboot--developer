package me.shinsunyoung.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;

<<<<<<< HEAD
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
=======
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder // ✅ builder() 메서드 생성됨
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
>>>>>>> 3ae93ff (develop1)
    private Long id;

    private String email;
    private String password;
    private String nickname;

    private String provider;
    private String providerId;

<<<<<<< HEAD
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
=======
    public User update(String name) {
        this.nickname = name;
        return this;
    }
>>>>>>> 3ae93ff (develop1)
}
