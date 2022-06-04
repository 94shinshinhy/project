package com.example.project.entity;

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

}
