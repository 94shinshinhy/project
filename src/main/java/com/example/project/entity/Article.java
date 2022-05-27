package com.example.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {

    @Id // 대표값을 지정 like a 주민등록번호
    @GeneratedValue // 자동생성 1, 2, 3, 4 ...
    private Long id;

    @Column // 테이블의 단위와 연결되게해줌
    private String title;

    @Column
    private String content;
}
