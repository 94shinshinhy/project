package com.example.project.service;

import com.example.project.dto.ArticleForm;
import com.example.project.entity.Article;
import com.example.project.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleServiceTest {
    @Autowired
    private ArticleService articleService;

    @Test
    void index() {
        // 예상
        Article a = new Article(1L, "11", "111");
        Article b = new Article(2L, "22", "222");
        Article c = new Article(3L, "33", "333");

        List<Article> expected = new ArrayList<Article>(Arrays.asList(a, b, c));

        // 실제
        List<Article> articleList = articleService.index();

        // 비교
        assertEquals(expected.toString(), articleList.toString());
    }

    @Test
    void show_success() {
        // 예상
        Long id = 1L;
        Article expected = new Article(1L, "11", "111");

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected.toString(), article.toString());
    }

    @Test
    void show_fail(){
        // 예상
        Long id = -1L;
        Article expected = null;

        // 실제
        Article article = articleService.show(id);

        // 비교
        assertEquals(expected, article);
    }

    @Test
    @Transactional
    void create_success() {
        // 예상
        String title = "44";
        String content = "444";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = new Article(4L, title, content);

        // 실제
        Article saved = articleService.create(dto);

        // 비교
        assertEquals(expected.toString(), saved.toString());
    }
    @Test
    @Transactional
    void create_fail() {
        // 예상
        Long id = 4L;
        String title = "44";
        String content = "444";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = null;

        // 실제
        Article saved = articleService.create(dto);

        // 비교
        assertEquals(expected, saved);
    }

    @Test
    @Transactional
    void update_id_title_content() {
        // 예상
        Long id = 1L;
        String title = "1234";
        String content = "1234";
        ArticleForm dto = new ArticleForm(id, title, content);
        Article expected = new Article(id, title, content);

        // 실제
        Article updated = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), updated.toString());
    }

    @Test
    @Transactional
    void update_id_title() {
        // 예상
        Long id = 1L;
        String title = "1234";
        String content = "1234";
        Article expected = new Article(id, title, "111");
        ArticleForm dto = new ArticleForm(id, title, null);

        // 실제
        Article updated = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), updated.toString());
    }

    @Test
    @Transactional
    void update_id() {
        // 예상
        Long id = 1L;
        ArticleForm dto = new ArticleForm(id, null, null);
        Article expected = new Article(id, "11", "111");

        // 실제
        Article updated = articleService.update(id, dto);

        // 비교
        assertEquals(expected.toString(), updated.toString());
    }

    @Test
    @Transactional
    void update_no_id() {
        // 예상
        String title = "1234";
        String content = "1234";
        ArticleForm dto = new ArticleForm(null, title, content);
        Article expected = null;

        // 실제
        Article updated = articleService.update(1L, dto);

        // 비교
        assertEquals(expected, updated);
    }

    @Test
    void delete_success() {
        // 예상
        Long id = 1L;
        Article expected = articleService.show(id);

        // 실제
        Article deleted = articleService.delete(id);

        // 비교
        assertEquals(expected.toString(), deleted.toString());
    }

    @Test
    void delete_fail() {
        // 예상
        Article expected = null;

        // 실제
        Article deleted = articleService.delete(-1L);

        // 비교
        assertEquals(expected, deleted);
    }
}