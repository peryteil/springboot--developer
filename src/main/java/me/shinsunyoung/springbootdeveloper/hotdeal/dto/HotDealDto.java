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
        this.imageDtos = hotDeal.getImages().stream()
                .map(img -> new ImageDto(img.getFileUrl()))
                .toList();
    }
}
