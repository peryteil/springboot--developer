package me.shinsunyoung.springbootdeveloper.mypage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyPageUserDto {
    private Long id;
    private String name;
    private String email;
    private String nickname;
    private String membership;
    private String role;
    private long totalOrders;
    private long totalReviews;
    private long totalLikes;
}
