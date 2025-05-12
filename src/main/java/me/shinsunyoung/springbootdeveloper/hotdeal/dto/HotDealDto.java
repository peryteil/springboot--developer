package me.shinsunyoung.springbootdeveloper.hotdeal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotDealDto {
    private String title;
    private String shopName;
    private String shopLink;
    private int price;
    private String content;
    private List<ImageDto> imageDtos = new ArrayList<>();

    public HotDealDto(HotDeal hotDeal) {
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
