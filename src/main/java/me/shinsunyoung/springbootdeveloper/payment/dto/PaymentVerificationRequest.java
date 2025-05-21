package me.shinsunyoung.springbootdeveloper.payment.dto;

import lombok.Getter;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.order.dto.CartItemDto;

import java.util.List;

@Getter
@Setter
public class PaymentVerificationRequest {
    private String imp_uid;
    private String merchant_uid;
    private int amount;
    private String name;
    private String tel;
    private String addr;
    private String postcode;
    private List<CartItemDto> cartItems;
}
