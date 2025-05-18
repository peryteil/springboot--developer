package me.shinsunyoung.springbootdeveloper.product.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductFindAll {

    private Long id;
    private String title;
    private Integer price;
    private String category;
    private String brand;
    private List<ImageDto> imageDtos = new ArrayList<>();

    public ProductFindAll(Long id, String title, Integer price, String category, String brand) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.category = category;
        this.brand = brand;
    }

    public ProductFindAll(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.brand = product.getBrand() != null ? product.getBrand().getTitle() : "브랜드 없음";

        if (product.getImages() != null) {
            this.imageDtos = product.getImages().stream()
                    .map(img -> new ImageDto(img.getFileUrl()))
                    .toList();
        }
    }

    public static ProductFindAll fromEntity(Product product) {
        ProductFindAll dto = new ProductFindAll();
        dto.id = product.getId();
        dto.title = product.getTitle();
        dto.price = product.getPrice();
        dto.category = product.getCategory();
        dto.brand = product.getBrand() != null ? product.getBrand().getTitle() : "브랜드 없음";

        if (product.getImages() != null) {
            dto.imageDtos = product.getImages().stream()
                    .map(img -> new ImageDto(img.getFileUrl()))
                    .toList();
        }

        return dto;
    }
}
