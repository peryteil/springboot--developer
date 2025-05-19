package me.shinsunyoung.springbootdeveloper.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.domain.User;
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
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // 🔁 변경됨
    private User user; // 🔁 Member → User

    @ManyToOne
    @JoinColumn(name = "orders", nullable = false)
    private Orders orders;

    @ManyToOne
    @JoinColumn(name = "product", nullable = false)
    private Product product;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_option")
    private String productOption;

    @Column(name = "product_price", nullable = false)
    private Integer price;

    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    @Column(name = "paid_at", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime paidAt;

    @Column(name = "status")
    private Boolean status = true;

    @Column(name = "review")
    private Boolean review = false;

    public PaymentHistory(User user, Orders orders, Product product, String productName, String productOption, Integer price, Long totalPrice) {
        this.user = user;
        this.orders = orders;
        this.product = product;
        this.productName = productName;
        this.productOption = productOption;
        this.price = price;
        this.totalPrice = totalPrice;
        this.paidAt = LocalDateTime.now();
    }

    public void setReview(Boolean review) {
        this.review = review;
    }
}
