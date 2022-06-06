package com.example.project.entity;

import com.example.project.dto.CommentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne // 해당 댓글 엔티티가 여러 개가 하나의 article에 연관됨
    @JoinColumn(name = "article_id") // article_id 칼럼에 Article의 대표값을 저장
    private Article article;

    @Column
    private String nickname;

    @Column
    private String body;

    public static Comment toEntity(Article article, CommentDto dto) {
        if(dto.getId() != null) throw new IllegalArgumentException("댓글의 id가 없어야함");
        if(article.getId() != dto.getArticleId()) throw new IllegalArgumentException("articlre id와 dto id가 다름");
        return new Comment(dto.getId(),
                article,
                dto.getNickname(),
                dto.getBody());
    }
}
