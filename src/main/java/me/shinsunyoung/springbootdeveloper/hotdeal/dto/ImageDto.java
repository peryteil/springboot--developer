package me.shinsunyoung.springbootdeveloper.hotdeal.dto;

import lombok.*;
import me.shinsunyoung.springbootdeveloper.config.s3.Image;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private String fileUrl;

    public static ImageDto fromEntity(Image image) {
        return new ImageDto(image.getFileUrl());
    }

}
