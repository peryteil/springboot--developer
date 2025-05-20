package me.shinsunyoung.springbootdeveloper.mypage.dto;

import lombok.Builder;
import me.shinsunyoung.springbootdeveloper.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MyPageUserDto {
    private Long id;
    private String name;
    private String email;
    private String nickname;
    private String role;
    private String membership;
    private Long totalOrder;
    private Long totalReviews;
    private Long totalLikes;

    public static MyPageUserDto from(User user) {
        return new MyPageUserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getNickname(),
                user.getRole(),
                user.getMembership(),
                user.getTotalOrder(),
                user.getTotalLikes(),
                user.getTotalReviews()
        );
    }
}
