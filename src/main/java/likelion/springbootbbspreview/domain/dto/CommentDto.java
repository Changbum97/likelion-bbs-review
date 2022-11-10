package likelion.springbootbbspreview.domain.dto;

import likelion.springbootbbspreview.domain.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
public class CommentDto {
    private Long articleId;
    private String comment;
}
