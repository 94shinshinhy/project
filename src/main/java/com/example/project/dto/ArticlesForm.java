package com.example.project.dto;

import com.example.project.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class ArticlesForm {

    private String title;
    private String content;

    public Article toEntity(){
        return new Article(null, title, content);
    }
}
