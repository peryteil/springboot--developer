package me.shinsunyoung.springbootdeveloper.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.cart.entity.Cart;
import me.shinsunyoung.springbootdeveloper.dealcomment.entity.DealComment;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;
import me.shinsunyoung.springbootdeveloper.order.entity.Orders;
import me.shinsunyoung.springbootdeveloper.review.entity.Review;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
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
    private String provider;
    private String providerId; // OAuth2 provider가 제공하는 유니크 ID

    private String name;
    private String membership;
    private String role;
    private String phone;
    private Long totalOrder;
    private Long totalReviews;
    private Long totalLikes;


    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotDeal> hotDeals = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DealComment> dealComments = new ArrayList<>();
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cart> carts = new ArrayList<>();

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders = new ArrayList<>();

    @Builder
    public User(String email, String password, String nickname, String provider, String providerId, String name, String membership, String role, Long totalOrder, Long totalReviews,Long totalLikes) {

        this.email = email;
        this.password = password;
        this.nickname = (nickname != null && !nickname.isBlank()) ? nickname : generateNickname(email);
        this.provider = provider;
        this.providerId = providerId;
        this.name = name;
        this.membership = membership;
        this.totalOrder = totalOrder;
        this.totalReviews = totalReviews;
        this.totalLikes = totalLikes;
        this.role = (role != null) ? role : "USER";
    }

    public static User socialUser(String email, String nickname, String provider, String providerId) {
        return User.builder()
                .email(email)
                .nickname(nickname)

               .password(null)
              .password(null) // DB 허용 시 null 가능

                .provider(provider)
                .providerId(providerId)
                .build();
    }


    public User update(String nickname) {
        this.nickname = nickname;
        return this;
    }

    // Spring Security UserDetails 구현 메서드들

    // 이메일 기반 닉네임 자동 생성기
    private static String generateNickname(String email) {
        if (email == null) return "사용자";
        return email.split("@")[0];
    }


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

