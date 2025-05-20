package me.shinsunyoung.springbootdeveloper.mypage.dto;

import me.shinsunyoung.springbootdeveloper.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyPageUserDto {
    private String name;
    private String email;
    private String nickname;
    private String role;
    private String membership;

    public static MyPageUserDto from(User user) {
        return new MyPageUserDto(
                user.getName(),
                user.getEmail(),
                user.getNickname(),
                user.getRole(),
                user.getMembership()
        );
    }
}
