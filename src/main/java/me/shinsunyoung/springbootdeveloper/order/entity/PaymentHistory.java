package me.shinsunyoung.springbootdeveloper.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "payment_history")
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_history_id")
    private Long id; // PK

    @ManyToOne
    @JoinColumn(name = "member", nullable = false)
    private Member member; // 사용자

    @ManyToOne
    @JoinColumn(name = "orders", nullable = false)
    private Orders orders; // 주문 테이블과 다대일 (연관관계 주인은 주문)

    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product; // 상품

    @Column(name = "product_name")
    private String productName; // 상품 이름

    @Column(name = "product_option")
    private String productOption; // 상품 옵션

    @Column(name = "product_price", nullable = false)
    private Integer price; // 가격

    @Column(name = "total_price", nullable = false)
    private Long totalPrice; // 결제한 총 가격

    @Column(name = "paid_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime paidAt; // 결제시각

    @Column(name = "status")
    private Boolean status = true; // 상태

    @Column(name = "review")
    private Boolean review = false; // 리뷰 작성 여부

    public PaymentHistory(Member member, Orders orders, Product product, String productName, String productOption, Integer price, Long totalPrice) {
        this.member = member;
        this.orders = orders;
        this.product = product;
        this.productName = productName;
        this.productOption = productOption;
        this.price = price;
        this.totalPrice = totalPrice;
        this.paidAt =  LocalDateTime.now();
    }

    public void setReview(Boolean review) {
        this.review = review;
    }
}
