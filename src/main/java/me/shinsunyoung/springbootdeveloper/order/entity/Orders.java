package me.shinsunyoung.springbootdeveloper.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId; // PK

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member; // 사용자

    // 상품 - 주문 테이블 다대다 구현, 중간 테이블 생성
    @ManyToMany
    @JoinTable(
            name = "orders_product_management",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "product_management_id")
    )
    private List<ProductManagement> productManagements = new ArrayList<>();

    @Column(name = "order_name")
    private String ordererName; // 주문자 이름

    @Column(name = "product_names")
    private String productName; // 상품 이름

    @Enumerated(EnumType.STRING)
    PayMethod payMethod; // 결제 방식

    @Column(length = 100, name = "merchant_uid")
    private String merchantUid; // 주문번호

    @Column(name = "total_price")
    private BigDecimal totalPrice; // 가격

    @Column(name = "address")
    private String address; // 주소

    @Column(name = "detail_address")
    private String detailAddress; // 상세주소

    @Column(name = "post_code", length = 100)
    private String postCode; // 우편번호

    @Column(name = "phone_number")
    private String phoneNumber; // 전화번호

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderDay; // 주문시각

    @Column(name = "payment_status")
    private Boolean paymentStatus = false; // 결제 상태

    @OneToMany(mappedBy = "orders")
    private List<PaymentHistory> paymentHistories = new ArrayList<>(); // 결제내역과 일대다


    public void orderConfirm(String merchantUid, OrderDto orderDto) {
        this.merchantUid = merchantUid;
        this.postCode = orderDto.getPostCode();
        this.address = orderDto.getAddress();
        this.detailAddress = orderDto.getDetailAddress();
        this.ordererName = orderDto.getOrdererName();
        this.phoneNumber = orderDto.getPhoneNumber();
        this.payMethod = orderDto.getPayMethod();
        this.orderDay = LocalDateTime.now();

    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
