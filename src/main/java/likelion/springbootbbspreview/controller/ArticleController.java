package likelion.springbootbbspreview.controller;

import likelion.springbootbbspreview.domain.dto.CreateArticleDto;
import likelion.springbootbbspreview.domain.entity.Article;
import likelion.springbootbbspreview.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(value = {"", "/"})
    public String home() {
        return "redirect:/articles/list";
    }

    @GetMapping("/new")
    public String newArticleForm() {
        return "articles/new";
    }

    @PostMapping("")
    public String createArticle(CreateArticleDto dto) {
        Article newArticle = dto.toEntity();
        articleRepository.save(newArticle);
        log.info("save {}", newArticle);
        return "redirect:/articles/list";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isEmpty()) {
            return "error";
        }

        Article findArticle = optionalArticle.get();
        log.info("findById {} : {}", id, findArticle);
        model.addAttribute("article", findArticle);
        return "articles/show";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<Article> articles = articleRepository.findAll();
        log.info("findAll : {}개", articles.size());
        model.addAttribute("articles", articles);
        return "articles/list";
    }
}
