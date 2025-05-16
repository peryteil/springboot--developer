package me.shinsunyoung.springbootdeveloper.brand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.brand.entity.Brand;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandResDto {
    private Long id;
    private String title;
    private Integer founded;
    private String introduction;//소개글
    private String history;
    private String country;
    private List<ImageDto> images = new ArrayList<>();

    public BrandResDto(Brand brand) {
        this.id = brand.getId();
        this.title = brand.getTitle();
        this.founded = brand.getFounded();
        this.introduction = brand.getIntroduction();
        this.history = brand.getHistory();
        this.country = brand.getCountry();
        this.images = brand.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
    }

    public static BrandResDto fromEntity(Brand brand) {
        BrandResDto dto = new BrandResDto();
        dto.id = brand.getId();
        dto.title = brand.getTitle();
        dto.founded = brand.getFounded();
        dto.introduction = brand.getIntroduction();
        dto.country = brand.getCountry();
        dto.history = brand.getHistory();
        dto.images = brand.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
        return dto;
    }
}
