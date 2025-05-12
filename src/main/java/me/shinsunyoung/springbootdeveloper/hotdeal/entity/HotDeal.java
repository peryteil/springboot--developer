package me.shinsunyoung.springbootdeveloper.hotdeal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.config.s3.Image;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class HotDeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String shopName;
    private String shopLink;
    private int price;
    private String content;
    private int viewCount;
    private int likeCount;

    @OneToMany(mappedBy = "hotDeal", cascade = CascadeType.ALL)
    private List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        if (images == null) {
            images = new ArrayList<>();
        }
        images.add(image);
        image.setHotDeal(this);
    }
}
