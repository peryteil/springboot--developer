package me.shinsunyoung.springbootdeveloper.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductFindAll {

    private Long id;
    private String title;
    private Integer price;
    private String category;
    private BrandDto brand;
    private List<ImageDto> imageDtos = new ArrayList<>();

    public ProductFindAll(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.brand = product.getBrand() != null ? new BrandDto(product.getBrand()) : null;

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
        dto.brand = product.getBrand() != null ? new BrandDto(product.getBrand()) : null;

        if (product.getImages() != null) {
            dto.imageDtos = product.getImages().stream()
                    .map(img -> new ImageDto(img.getFileUrl()))
                    .toList();
        }

        return dto;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BrandDto {
        private Long id;
        private String title;
        private String content;
        private Integer founded;
        private String office;
        private String representative;//대표제품
        private String webSite;
        private String introduction;//소개글
        private String history;
        private String country;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private List<ImageDto> images = new ArrayList<>();


        public BrandDto(Brand brand) {
            this.id = brand.getId();
            this.title = brand.getTitle();
            this.content = brand.getContent();
            this.founded = brand.getFounded();
            this.office = brand.getOffice();
            this.representative = brand.getRepresentative();
            this.webSite = brand.getWebSite();
            this.introduction = brand.getIntroduction();
            this.history = brand.getHistory();
            this.country = brand.getCountry();
            this.createdAt = brand.getCreatedAt();
            this.updatedAt = brand.getUpdatedAt();
            this.images = brand.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
        }

        public static BrandDto fromEntity(Brand brand) {
            BrandDto dto = new BrandDto();
            dto.id = brand.getId();
            dto.title = brand.getTitle();
            dto.content = brand.getContent();
            dto.founded = brand.getFounded();
            dto.office = brand.getOffice();
            dto.representative = brand.getRepresentative();
            dto.webSite = brand.getWebSite();
            dto.introduction = brand.getIntroduction();
            dto.country = brand.getCountry();
            dto.history = brand.getHistory();
            dto.createdAt = brand.getCreatedAt();
            dto.updatedAt = brand.getUpdatedAt();
            dto.images = brand.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
            return dto;
        }
    }
}
