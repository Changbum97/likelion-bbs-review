package likelion.springbootbbsreview.repository;

import likelion.springbootbbsreview.domain.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
