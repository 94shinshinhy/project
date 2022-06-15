package com.example.project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity // DB가 해당 객체를 인식 가능하게 해줌
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Article {
    @Id // 대표값을 지정 like a 주민등록번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 id를 자동 생성해줌
    private Long id;

    @Column // 테이블의 단위와 연결되게해줌
    private String title;

    @Column
    private String content;

    /* fetch : LAZY는 지연 로딩, EAGER는 즉시 로딩, @xxToMany의 디폴트는 LAZY, @xxToOne의 디폴트는 EAGER
       mappedBy : 양방향 연관관계일 때 반대쪽에 매핑되는 엔티티의 필드값
       cascade : 영속성전이(특정 엔티티를 영속 상태로 만들 때 연관된 엔티티도 함계 영속 상태로 전이됨)
       orphanRemoval : 고아 객체를 삭제해주는 역할
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> Comment = new ArrayList<>();
    */

    public void patch(Article article){
        if(article.title != null) this.title = article.title;
        if(article.content != null) this.content = article.content;
    }
}
