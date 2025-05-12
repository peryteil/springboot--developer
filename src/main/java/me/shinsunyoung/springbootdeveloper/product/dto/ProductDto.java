package me.shinsunyoung.springbootdeveloper.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String title;
    private Integer price;
    private String materials;
    private String explamationDate;
    private String weight;
    private String origin;//원산지
    private Integer stock;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ImageDto> dtos = new ArrayList<>();


    public ProductDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.materials = product.getMaterials();
        this.explamationDate = product.getExplamationDate();
        this.weight = product.getWeight();
        this.origin = product.getOrigin();
        this.stock = product.getStock();
        this.content = product.getContent();
        this.dtos = product.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();

    }
}
