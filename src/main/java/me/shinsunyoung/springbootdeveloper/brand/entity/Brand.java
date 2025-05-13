package me.shinsunyoung.springbootdeveloper.brand.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.config.s3.Image;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private Integer season;
    private String office;
    private String country;
    private String representative;//대표제품
    private String webSite;
    private String introduction;//소개글
    private String history;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        if (image == null) {
            images = new ArrayList<>();
        }
        images.add(image);
        image.setBrand(this);

    }


}
