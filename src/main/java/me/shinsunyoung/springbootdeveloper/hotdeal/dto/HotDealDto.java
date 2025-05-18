package me.shinsunyoung.springbootdeveloper.hotdeal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.dealcomment.dto.DealCommentDto;
import me.shinsunyoung.springbootdeveloper.dealcomment.entity.DealComment;
import me.shinsunyoung.springbootdeveloper.domain.User;
import me.shinsunyoung.springbootdeveloper.domain.UserDto;
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
    private String category;
    private String content;
    private Integer likeCount;
    private Integer viewCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private UserDto userDto;
    private List<ImageDto> imageDtos = new ArrayList<>();
    private List<DealCommentDto> dtos = new ArrayList<>();

    public HotDealDto(HotDeal hotDeal) {
        this.id = hotDeal.getId();
        this.title = hotDeal.getTitle();
        this.shopName = hotDeal.getShopName();
        this.shopLink = hotDeal.getShopLink();
        this.price = hotDeal.getPrice();
        this.content = hotDeal.getContent();
        this.category = hotDeal.getCategory();
        this.likeCount = hotDeal.getLikeCount();
        this.viewCount = hotDeal.getViewCount();
        this.createdAt = hotDeal.getCreatedAt();
        this.updatedAt = hotDeal.getUpdatedAt();
        this.dtos = hotDeal.getDealComments().stream().map(DealCommentDto::new).toList();
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
        dto.category = hotDeal.getCategory();
        dto.price = hotDeal.getPrice();
        dto.likeCount = hotDeal.getLikeCount();
        dto.viewCount = hotDeal.getViewCount();
        dto.content = hotDeal.getContent();
        dto.createdAt = hotDeal.getCreatedAt();
        dto.updatedAt = hotDeal.getUpdatedAt();
        dto.userDto = new UserDto(hotDeal.getUser());
        dto.dtos = hotDeal.getDealComments().stream().map(DealCommentDto::new).toList();
        dto.imageDtos = hotDeal.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
        return dto;
    }
}
