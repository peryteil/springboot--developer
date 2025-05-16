package me.shinsunyoung.springbootdeveloper.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.shinsunyoung.springbootdeveloper.hotdeal.dto.ImageDto;
import me.shinsunyoung.springbootdeveloper.product.entity.Product;
import me.shinsunyoung.springbootdeveloper.review.dto.ReviewDto;
import me.shinsunyoung.springbootdeveloper.review.entity.Review;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRelate {
    private Long id;
    private String title;
    private Integer price;
    private String category;
    private List<ImageDto> imageDtos = new ArrayList<>();


    public ProductRelate(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.category = product.getCategory();
        this.imageDtos = product.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
    }

    public static ProductRelate fromEntity(Product product) {
        ProductRelate dto = new ProductRelate();
        dto.id = product.getId();
        dto.title = product.getTitle();
        dto.price = product.getPrice();
        dto.category = product.getCategory();
        dto.imageDtos = product.getImages().stream().map(img -> new ImageDto(img.getFileUrl())).toList();
        if (product.getReviews() != null && !product.getReviews().isEmpty()) {
            double avg = product.getReviews().stream().filter(r -> r.getRating() != null)
                    .mapToInt(Review::getRating).average().orElse(0.0);
        }

        return dto;
    }
}
