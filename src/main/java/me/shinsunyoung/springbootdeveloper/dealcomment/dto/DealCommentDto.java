package me.shinsunyoung.springbootdeveloper.dealcomment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.dealcomment.entity.DealComment;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealCommentDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DealCommentDto(DealComment dealComment) {
        this.id = dealComment.getId();
        this.content = dealComment.getContent();
        this.createdAt = dealComment.getCreatedAt();
        this.updatedAt = dealComment.getUpdatedAt();

    }
}
