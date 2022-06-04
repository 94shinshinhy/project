package com.example.project.repository;

import com.example.project.entity.Article;
import com.example.project.entity.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;

    @Test
    @DisplayName("해당 게시글 댓글 조회")
    void findByArticleId() {
        /* case 1 : 4번 게시글 댓글 조회 */
        {
            // 데이터
            Long articleId = 4L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            Article article = new Article(4L, "영화", "댓글1");
            Comment a = new Comment(1L, article, "SHIN", "닥터스트레인지");
            Comment b = new Comment(2L, article, "KIM", "범죄도시2");
            Comment c = new Comment(3L, article, "LEE", "연애의온도");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(comments.toString(), expected.toString(), "게시글의 모든 댓글 조회");
        }
        /* case 2 : 1번 게시글의 댓글 조회 */
        {
            // 데이터
            Long articleId = 1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            Article article = new Article(1L, "11", "111");
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(comments.toString(), expected.toString(), "게시글의 모든 댓글 조회");
        }
        /* case 3 : 9번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터
            Long articleId = 9L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(comments, expected);
        }
        /* case 4 : 9999번 게시글의 모든 댓글 조회 */
        {
            // 입력 데이터
            Long articleId = 9999L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(comments, expected);
        }
        /* case 5 : -1 게시글의 모든 댓글 조회 */
        {
            // 입력데이터
            Long articleId = -1L;

            // 실제 수행
            List<Comment> comments = commentRepository.findByArticleId(articleId);

            // 예상
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(comments, expected);
        }
    }

    @Test
    @DisplayName("특정 닉네임의 모든 댓글 조회")
    void findByNickname() {
        /* case 1 : park의 모든 댓글 조회 */
        {
            // 데이터
            String nickname = "SHIN";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상
            Comment a = new Comment(1L, new Article(4L, "영화", "댓글1"), "SHIN", "닥터스트레인지");
            Comment b = new Comment(4L, new Article(5L, "음식", "댓글2"), "SHIN", "치킨");
            Comment c = new Comment(7L, new Article(6L, "취미", "댓글3"), "SHIN", "축구");
            List<Comment> expected = Arrays.asList(a, b, c);

            // 검증
            assertEquals(comments.toString(), expected.toString());
        }
        /* case 2 : null의 모든 댓글 */
        {
            // 데이터
            String nickname = null;

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(comments, expected);
        }
        /* case 3 : ""의 모든 댓글 조회 */
        {
            // 데이터
            String nickname = "";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(comments, expected);
        }
        /* case 4 : "i"의 모든 댓글 조회 */
        {
            // 데이터
            String nickname = "i";

            // 실제 수행
            List<Comment> comments = commentRepository.findByNickname(nickname);

            // 예상
            List<Comment> expected = Arrays.asList();

            // 검증
            assertEquals(comments, expected);
        }
    }
}