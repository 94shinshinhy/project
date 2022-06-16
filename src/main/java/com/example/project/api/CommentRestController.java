package com.example.project.api;

import com.example.project.annotation.RunningTime;
import com.example.project.dto.CommentDto;
import com.example.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentRestController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        List<CommentDto> comments = commentService.comments(articleId);

        return ResponseEntity.status(HttpStatus.OK).body(comments);
    }

    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long articleId,
                                                    @RequestBody CommentDto dto){
        CommentDto created = commentService.createComment(articleId, dto);

        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @PatchMapping("/api/articles/comments/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long id,
                                                    @RequestBody CommentDto dto){
        CommentDto updated = commentService.updateComment(id, dto);

        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/api/articles/comments/{id}")
    @RunningTime
    public ResponseEntity<CommentDto> deleteComment(@PathVariable Long id){
        CommentDto deleted = commentService.deleteComment(id);

        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }
}
