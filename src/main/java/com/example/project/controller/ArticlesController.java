package com.example.project.controller;

import com.example.project.entity.Article;
import com.example.project.dto.ArticleForm;
import com.example.project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
    public String create(ArticleForm af){
        log.info(af.toString());

        // dto를 entity로 변환
        Article article = af.toEntity();
        log.info(article.toString());
        
        // repository에게 entity를 db에 저장하도록 함
        Article saved = articleRepository.save(article);
        log.info(saved.toString());

        return "redirect:/articles/" + saved.getId();
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model){ // path로부터 오는 id 값을 가져오는 어노테이션
        log.info("id : " + id);

        Article article = articleRepository.findById(id).orElse(null); // 데이터가 없으면 null값 반환

        model.addAttribute("article", article);

        return "articles/show";
    }

    @GetMapping("/articles")
    public String index(Model model){
        List<Article> articleList = articleRepository.findAll();
        model.addAttribute("articleList", articleList);

        return "articles/index";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        Article articleEntity = articleRepository.findById(id).orElse(null);

        model.addAttribute("article", articleEntity);

        return "articles/edit";
    }

    @PostMapping("/articles/update")
    public String update(ArticleForm articleForm, RedirectAttributes rttr){
        // entity로 변환
        Article articleEntity = articleForm.toEntity();

        // 기존 데이터 존재 확인
        Article target = articleRepository.findById(articleEntity.getId()).orElse(null);

        // 기존 데이터가 존재할 경우 save
        if(target != null) {
            Article save = articleRepository.save(articleEntity);
            rttr.addFlashAttribute("msg", "수정이 완료되었습니다.");
        }

        return "redirect:/articles/" + articleEntity.getId();
    }

    @GetMapping("/articles/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        // 데이터가 존재하는지 확인
        Article target = articleRepository.findById(id).orElse(null);

        // 데이터가 존재하면 삭제
        if(target != null){
            articleRepository.delete(target);
            rttr.addFlashAttribute("msg", "삭제가 완료되었습니다.");
        }

        return "redirect:/articles";
    }
}
