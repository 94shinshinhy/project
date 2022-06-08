package com.example.project.service;

import com.example.project.dto.CommentDto;
import com.example.project.entity.Article;
import com.example.project.entity.Comment;
import com.example.project.repository.ArticleRepository;
import com.example.project.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleRepository articleRepository;

    public List<CommentDto> comments(Long articleId) {
        return commentRepository.findByArticleId(articleId)
                .stream()
                .map(comment -> CommentDto.createCommentDto(comment))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentDto createComment(Long articleId, CommentDto dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지않음"));

        Comment comment = Comment.toEntity(article, dto);

        Comment created = commentRepository.save(comment);

        return CommentDto.createCommentDto(created);
    }

    public CommentDto updateComment(Long id, CommentDto dto) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지않음"));

        comment.patch(dto);

        Comment updated = commentRepository.save(comment);
        
        return CommentDto.createCommentDto(updated);
    }

    public CommentDto deleteComment(Long id) {
        Comment deleted = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("댓글이 존재하지않음"));

        commentRepository.delete(deleted);

        return CommentDto.createCommentDto(deleted);
    }
}
