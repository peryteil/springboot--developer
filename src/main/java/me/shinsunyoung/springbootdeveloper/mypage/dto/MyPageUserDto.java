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
    private String name;
    private String email;
    private String membership;
    private String role;
    private long orders;
    private long reviews;
    private long likes;
}
