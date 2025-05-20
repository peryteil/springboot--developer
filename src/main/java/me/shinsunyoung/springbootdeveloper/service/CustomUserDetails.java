package me.shinsunyoung.springbootdeveloper.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {
    private final Long userId;
    private final String nickname;
    private final String membership;

    public CustomUserDetails(Long userId, String email, String nickname, String membership, Collection<? extends GrantedAuthority> authorities) {
        super(email, "", authorities);
        this.userId = userId;
        this.nickname = nickname;
        this.membership = membership;
    }


    public Long getUserId() { return userId; }
    public String getNickname() { return nickname; }
    public String getMembership() { return membership; }
}
