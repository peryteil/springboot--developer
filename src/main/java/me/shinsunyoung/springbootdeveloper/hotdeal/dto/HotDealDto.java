package me.shinsunyoung.springbootdeveloper.hotdeal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotDealDto {
    private Long id;
    private String title;
    private String shopName;
    private String shopLink;
    private Integer price;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<ImageDto> imageDtos = new ArrayList<>();

    public HotDealDto(HotDeal hotDeal) {
        this.id = hotDeal.getId();
        this.title = hotDeal.getTitle();
        this.shopName = hotDeal.getShopName();
        this.shopLink = hotDeal.getShopLink();
        this.price = hotDeal.getPrice();
        this.content = hotDeal.getContent();
        this.createdAt = hotDeal.getCreatedAt();
        this.updatedAt = hotDeal.getUpdatedAt();
        this.imageDtos = hotDeal.getImages().stream()
                .map(img -> new ImageDto(img.getFileUrl()))
                .toList();
    }

    public static HotDealDto fromEntity(HotDeal hotDeal) {
        HotDealDto dto = new HotDealDto();
        dto.id = hotDeal.getId();
        dto.title = hotDeal.getTitle();
        dto.shopLink = hotDeal.getShopLink();
        dto.shopName = hotDeal.getShopName();
        dto.price = hotDeal.getPrice();
        dto.content = hotDeal.getContent();
        dto.createdAt = hotDeal.getCreatedAt();
        dto.updatedAt = hotDeal.getUpdatedAt();
        dto.imageDtos = hotDeal.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
        return dto;
    }
}
