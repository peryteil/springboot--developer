package me.shinsunyoung.springbootdeveloper.mypage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class MyOrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long OrderDetKey; // 주문 상세번호

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "orderKey")
    private TotalOrder order; // 주문번호

    private Integer itemKey;
    private Integer price;
    private Integer cnt =1;
}
