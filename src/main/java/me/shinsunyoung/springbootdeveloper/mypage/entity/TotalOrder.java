package me.shinsunyoung.springbootdeveloper.mypage.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class TotalOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderKey;

    private Integer custKey;
    private Integer price;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate = new Date();

    private String card;
    private String status = "0";
    private String reason;

    private String name;
    private String zipcode;
    private String addr;
    private String addrDetail;
    private String phone;
    private String req;

    private Integer itemCNT;
    private String itemName;
    private String itemImg;

    // 연관 관계 매핑 (1:N)

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<MyOrderDetail> myOrderDetails;
}
