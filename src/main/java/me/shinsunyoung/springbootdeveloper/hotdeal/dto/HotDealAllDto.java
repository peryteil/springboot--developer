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
public class HotDealAllDto {
    private Long id;
    private String title;
    private String shopName;
    private String category;
    private Integer likeCount;
    private Integer viewCount;
    private LocalDateTime createdAt;

    public HotDealAllDto(HotDeal hotDeal) {
        this.id = hotDeal.getId();
        this.title = hotDeal.getTitle();
        this.shopName = hotDeal.getShopName();
        this.category = hotDeal.getCategory();
        this.likeCount = hotDeal.getLikeCount();
        this.viewCount = hotDeal.getViewCount();
        this.createdAt = hotDeal.getCreatedAt();
    }

    public static HotDealAllDto fromEntity(HotDeal hotDeal) {
        HotDealAllDto dto = new HotDealAllDto();
        dto.id = hotDeal.getId();
        dto.title = hotDeal.getTitle();
        dto.shopName = hotDeal.getShopName();
        dto.category = hotDeal.getCategory();
        dto.likeCount = hotDeal.getLikeCount();
        dto.viewCount = hotDeal.getViewCount();
        dto.createdAt = hotDeal.getCreatedAt();
        return dto;
    }
}
