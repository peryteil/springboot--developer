package me.shinsunyoung.springbootdeveloper.domain;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String nickname;
    private String provider;
    private String providerId; // OAuth2 provider가 제공하는 유니크 ID
    private String name;
    private String membership;
    private String role;

    public UserDto(User user) {
        if (user == null) {
            // 모든 필드에 기본값 세팅
            this.id = null;
            this.email = null;
            this.nickname = null;
            this.provider = null;
            this.providerId = null;
            this.name = null;
            this.membership = null;
            this.role = null;
            return;
        }

        this.id = user.getId();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.provider = user.getProvider();
        this.providerId = user.getProviderId();
        this.name = user.getName();
        this.membership = user.getMembership();
        this.role = user.getRole();
    }
}
