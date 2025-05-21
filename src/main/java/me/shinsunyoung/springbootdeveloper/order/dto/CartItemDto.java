package me.shinsunyoung.springbootdeveloper.order.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

// CartItemDto.java
@Getter
@Setter
@Data
public class CartItemDto {
    private Long productId;
    private int count;
}
