package likelion.springbootbbspreview.controller;

import likelion.springbootbbspreview.domain.dto.CreateArticleDto;
import likelion.springbootbbspreview.domain.entity.Article;
import likelion.springbootbbspreview.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("")
    public String createArticle(CreateArticleDto dto) {
        System.out.println(dto.getTitle());
        Article newArticle = dto.toEntity();
        articleRepository.save(newArticle);
        log.info("save : {}", newArticle);
        return "redirect:/articles/new";
    }
}
