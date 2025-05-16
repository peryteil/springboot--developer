package me.shinsunyoung.springbootdeveloper.hotdeal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.dealcomment.dto.DealCommentDto;
import me.shinsunyoung.springbootdeveloper.hotdeal.entity.HotDeal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotDealMain {
    private Long id;
    private String title;
    private String shopName;
    private Integer price;
    private Integer likeCount;
    private Integer viewCount;
    private List<ImageDto> imageDtos = new ArrayList<>();
    private List<DealCommentDto> dtos = new ArrayList<>();

    public HotDealMain(HotDeal hotDeal) {
        this.id = hotDeal.getId();
        this.title = hotDeal.getTitle();
        this.shopName = hotDeal.getShopName();
        this.price = hotDeal.getPrice();
        this.likeCount = hotDeal.getLikeCount();
        this.viewCount = hotDeal.getViewCount();
        this.dtos = hotDeal.getDealComments().stream().map(DealCommentDto::new).toList();
        this.imageDtos = hotDeal.getImages().stream()
                .map(img -> new ImageDto(img.getFileUrl()))
                .toList();
    }

    public static HotDealMain fromEntity(HotDeal hotDeal) {
        HotDealMain dto = new HotDealMain();
        dto.id = hotDeal.getId();
        dto.title = hotDeal.getTitle();
        dto.shopName = hotDeal.getShopName();
        dto.price = hotDeal.getPrice();
        dto.likeCount = hotDeal.getLikeCount();
        dto.viewCount = hotDeal.getViewCount();
        dto.dtos = hotDeal.getDealComments().stream().map(DealCommentDto::new).toList();
        dto.imageDtos = hotDeal.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
        return dto;
    }
}
