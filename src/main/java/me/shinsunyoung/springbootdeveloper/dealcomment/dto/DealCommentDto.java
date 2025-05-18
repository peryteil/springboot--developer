package me.shinsunyoung.springbootdeveloper.dealcomment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.shinsunyoung.springbootdeveloper.dealcomment.entity.DealComment;
import me.shinsunyoung.springbootdeveloper.domain.UserDto;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealCommentDto {
    private Long id;
    private String content;
    private UserDto userDto;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public DealCommentDto(DealComment dealComment) {
        this.id = dealComment.getId();
        this.content = dealComment.getContent();
        this.createdAt = dealComment.getCreatedAt();
        this.updatedAt = dealComment.getUpdatedAt();
        if (dealComment.getUser() != null) {
            this.userDto = new UserDto(dealComment.getUser());
        }
    }
}
