package me.shinsunyoung.springbootdeveloper.hotdeal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.config.s3.Image;
import me.shinsunyoung.springbootdeveloper.dealcomment.entity.DealComment;
import me.shinsunyoung.springbootdeveloper.domain.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "hot_deal", indexes = {
        @Index(name = "idx_created_at", columnList = "createdAt")})
public class HotDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String shopName;
    private String shopLink;
    private Integer price;
    private String content;
    private Integer viewCount=0;
    private String category;
    private Integer likeCount=0;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "hotDeal", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<DealComment> dealComments = new ArrayList<>();

    @OneToMany(mappedBy = "hotDeal", cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;
    public void addImage(Image image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
        image.setHotDeal(this);
    }
}
