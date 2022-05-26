package com.example.project.controller;

import com.example.project.entity.Article;
import com.example.project.dto.ArticlesForm;
import com.example.project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j // 로깅을 위한 어노테이션
public class ArticlesController {

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String newArticlesForm(){
        return "articles/new";
    }

    @PostMapping("/articles/create")
    public String create(ArticlesForm af){
        log.info(af.toString());

        // dto를 entity로 변환
        Article article = af.toEntity();
        log.info(article.toString());
        
        // repository에게 entity를 db에 저장하도록 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){ // path로부터 오는 id 값을 가져옴
        log.info("id : " + id);

        Article article = articleRepository.findById(id).orElse(null); // 데이터가 없으면 null값 반환

        model.addAttribute("article", article);

        return "articles/show";
    }
}
