package me.shinsunyoung.springbootdeveloper.brand.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDto {
    private Long id;
    private String title;
    private String description;
    private Integer popularity;
    private String country;
    private String founder;
    private String foundedYear;
    private String website;
    private String email;
    private String phone;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ImageDto> imageDtos;

    // ✅ 필요한 생성자 추가
    public BrandDto(Long id, String title) {
        this.id = id;
        this.title = title;
    }
}