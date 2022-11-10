package likelion.springbootbbspreview.repository;

import likelion.springbootbbspreview.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
