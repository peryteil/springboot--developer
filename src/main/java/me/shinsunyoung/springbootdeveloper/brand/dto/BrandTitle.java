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
public class BrandTitle {
    private Long id;
    private String title;

    public BrandTitle(Brand brand) {
        this.id = brand.getId();
        this.title = brand.getTitle();
    }

    public static BrandTitle fromEntity(Brand brand) {
        BrandTitle dto = new BrandTitle();
        dto.id = brand.getId();
        dto.title = brand.getTitle();
        return dto;
    }
}
