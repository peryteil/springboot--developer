package me.shinsunyoung.springbootdeveloper.mypage.dto;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import me.shinsunyoung.springbootdeveloper.domain.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MyOrderDto {
    private Long orderId;
    private String productName;
    private BigDecimal totalPrice;
    private LocalDateTime orderDay;
}