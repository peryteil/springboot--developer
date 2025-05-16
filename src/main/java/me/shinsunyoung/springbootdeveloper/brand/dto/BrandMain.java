package me.shinsunyoung.springbootdeveloper.brand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandMain {
    private Long id;
    private String title;
    private String content;
    private List<ImageDto> images = new ArrayList<>();

    public BrandMain(Brand brand) {
        this.id = brand.getId();
        this.title = brand.getTitle();
        this.content = brand.getContent();
        this.images = brand.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
    }

    public static BrandMain fromEntity(Brand brand) {
        BrandMain dto = new BrandMain();
        dto.id = brand.getId();
        dto.content = brand.getContent();
        dto.title = brand.getTitle();
        dto.images = brand.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
        return dto;
    }
}
