package likelion.springbootbbspreview.domain.dto;

import likelion.springbootbbspreview.domain.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateArticleDto {
    private String title;
    private String content;

    public Article toEntity() {
        return new Article(title, content);
    }
}
