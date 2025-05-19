package me.shinsunyoung.springbootdeveloper.order.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.order.entity.Size;

import java.awt.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ProductManagement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 어떤 상품인지
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 어떤 색상인지
    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    // 어떤 사이즈인지
    @ManyToOne
    @JoinColumn(name = "size_id", nullable = false)
    private Size size;

    // 재고 수량
    private Integer quantity;

    public ProductManagement(Product product, Color color, Size size, Integer quantity) {
        this.product = product;
        this.color = color;
        this.size = size;
        this.quantity = quantity;
    }
}
