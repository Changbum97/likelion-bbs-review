package likelion.springbootbbspreview.controller;

import likelion.springbootbbspreview.domain.dto.ArticleDto;
import likelion.springbootbbspreview.domain.dto.CommentDto;
import likelion.springbootbbspreview.domain.entity.Article;
import likelion.springbootbbspreview.domain.entity.Comment;
import likelion.springbootbbspreview.repository.ArticleRepository;
import likelion.springbootbbspreview.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
@Slf4j
public class ArticleController {

    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public ArticleController(ArticleRepository articleRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.commentRepository = commentRepository;
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
    public String createArticle(ArticleDto dto) {
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

        List<Comment> comments = commentRepository.findByArticleId(id);
        model.addAttribute("comments", comments);
        return "articles/show";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<Article> articles = articleRepository.findAll();
        log.info("findAll : {}개", articles.size());
        model.addAttribute("articles", articles);
        return "articles/list";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Optional<Article> optionalArticle = articleRepository.findById(id);
        if(optionalArticle.isEmpty()) {
            return "error";
        }

        model.addAttribute("article", optionalArticle.get());
        return "articles/edit";
    }

    @PutMapping("")
    public String editArticle(ArticleDto dto) {
        articleRepository.save(dto.toEntity());
        log.info("edit : {}", dto.toEntity());
        return "redirect:/articles/" + dto.getId();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        articleRepository.deleteById(id);
        log.info("deleteById : {}", id);
        return "redirect:/articles/list";
    }

    @PostMapping("/{id}/comment")
    public String saveComment(@PathVariable Long id, CommentDto dto) {
        Comment newComment = new Comment(id, dto.getComment());
        log.info("save comment : {}", newComment.toString());
        commentRepository.save(newComment);

        return "redirect:/articles/" + id;
    }
}
