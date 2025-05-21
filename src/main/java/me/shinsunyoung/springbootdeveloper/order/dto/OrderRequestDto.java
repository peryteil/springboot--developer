package me.shinsunyoung.springbootdeveloper.order.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
@Builder
@Data
public class OrderRequestDto {
    private String name;
    private String email;
    private String tel;
    private String addr;
    private String postcode;
    private String impUid;
    private String merchantUid;
    private BigDecimal amount;
    private List<CartItemDto> cartItems;
}