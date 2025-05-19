package me.shinsunyoung.springbootdeveloper.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.order.dto.OrderDto;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 🔁 변경됨
    private User user; // 🔁 Member → User

    @ManyToMany
    @JoinTable(
            name = "orders_product_management",
            joinColumns = @JoinColumn(name = "orders_id"),
            inverseJoinColumns = @JoinColumn(name = "product_management_id")
    )
    private List<ProductManagement> productManagements = new ArrayList<>();

    @Column(name = "order_name")
    private String ordererName;

    @Column(name = "product_names")
    private String productName;

    @Enumerated(EnumType.STRING)
    private PayMethod payMethod;

    @Column(length = 100, name = "merchant_uid")
    private String merchantUid;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "address")
    private String address;

    @Column(name = "detail_address")
    private String detailAddress;

    @Column(name = "post_code", length = 100)
    private String postCode;

    @Column(name = "phone_number")
    private String phoneNumber;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime orderDay;

    @Column(name = "payment_status")
    private Boolean paymentStatus = false;

    @OneToMany(mappedBy = "orders")
    private List<PaymentHistory> paymentHistories = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails = new ArrayList<>();

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
    public Orders(User user,
                  List<ProductManagement> productManagements,
                  String ordererName,
                  String productName,
                  BigDecimal totalPrice,
                  String phoneNumber) {
        this.user = user;
        this.productManagements = productManagements;
        this.ordererName = ordererName;
        this.productName = productName;
        this.totalPrice = totalPrice;
        this.phoneNumber = phoneNumber;
        this.paymentStatus = false; // 기본값 설정
    }
}
