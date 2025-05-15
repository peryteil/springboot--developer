package me.shinsunyoung.springbootdeveloper.mypage.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;


@Getter
@NoArgsConstructor
public class Review {
    @Id
    @GeneratedValue
    private Long id;
    private String productName;
    private String content;
    private int rating;

    @ManyToOne
    private User user;
}
