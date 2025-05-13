package me.shinsunyoung.springbootdeveloper.config.s3;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileUrl;


    @ManyToOne
    @JoinColumn(name = "hotDeal_id")
    private HotDeal hotDeal;

    @ManyToOne
    @JoinColumn(name = "product_ud")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;
}
