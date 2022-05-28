package com.example.project.api;

import com.example.project.dto.ArticleForm;
import com.example.project.entity.Article;
import com.example.project.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // RestAPI용 컨트롤러, 데이터(JSON)을 반환
public class ArticleRestController {

    @Autowired // DI
    private ArticleRepository articleRepository;

    // GET
    @GetMapping("/api/article")
    public List<Article> index(){
        return articleRepository.findAll();
    }

    @GetMapping("/api/article/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }

    // POST
    @PostMapping("/api/article")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        return articleRepository.save(article);
    }

    // PATCH
    @PatchMapping("/api/article/{id}")
    public ResponseEntity<Article> update(@PathVariable Long id,
                                          @RequestBody ArticleForm dto){
        // 1. entity 변환
        Article article = dto.toEntity();
        // 2. 대상 조회
        Article target = articleRepository.findById(id).orElse(null);
        // 3. 잘못된 요청
        if(target == null || id != article.getId()){
            log.info("잘못된 요청! id : {}, article : {}", id, article.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 성공
        target.patch(article); // 요청받은 데이터가 null일 때 기존 데이터로 설정해줌
        Article updated = articleRepository.save(target);
        // 5. 리턴
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    // DELETE
    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<Article> delete(@PathVariable Long id){
        // 1. 대상 확인
        Article article = articleRepository.findById(id).orElse(null);
        // 2. 잘못된 요청
        if(article == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        // 3. 삭제
        articleRepository.delete(article);
        // 4. 리턴
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
