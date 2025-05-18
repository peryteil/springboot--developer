package me.shinsunyoung.springbootdeveloper.mypage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.domain.User;

@Entity
@Table(name = "orders")
@Getter
@NoArgsConstructor
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productName;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
