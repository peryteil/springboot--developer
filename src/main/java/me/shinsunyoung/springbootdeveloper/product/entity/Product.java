package me.shinsunyoung.springbootdeveloper.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.config.s3.Image;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer price;
    private String materials;//재료
    private String explamationDate;//유통기한
    private String weight;
    private String origin;//원산지
    private Integer stock;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "product" , cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();


    public void addImage(Image image) {
        if (image == null) {
            images = new ArrayList<>();
        }
        images.add(image);
        image.setProduct(this);

    }

}
