package likelion.springbootbbspreview.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "Article2")
@Getter
@NoArgsConstructor
@ToString
public class Article {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
