package me.shinsunyoung.springbootdeveloper.order.dto;

import lombok.Getter;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.order.entity.PayMethod;

@Getter
@Setter
public class OrderDto {
    private String ordererName;
    private String address;
    private String detailAddress;
    private String postCode;
    private String phoneNumber;
    private PayMethod payMethod;
}
