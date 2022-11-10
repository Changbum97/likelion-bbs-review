package likelion.springbootbbsreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentDto {
    private Long articleId;
    private String comment;
}
