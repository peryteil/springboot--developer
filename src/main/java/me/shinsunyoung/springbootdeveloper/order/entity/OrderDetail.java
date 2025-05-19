package me.shinsunyoung.springbootdeveloper.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 하나의 주문에 여러 개의 상세 항목
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order; // ✅ 필드명 'order' 추가

    // 각각의 상품 정보
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    private int quantity;

    private int price;
}
