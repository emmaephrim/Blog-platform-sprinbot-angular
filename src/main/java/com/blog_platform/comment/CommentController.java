package com.blog_platform.comment;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Comment>> getAllComments() {
        return ResponseEntity.ok(commentService.findAllComments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable String id) {
        return ResponseEntity.ok(commentService.findCommentById(id));
    }

    @GetMapping("/post/{postId}")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable String postId) {
        return ResponseEntity.ok(commentService.findCommentsByPostId(postId));
    }

    @PostMapping("")
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment comment) {
        commentService.saveComment(comment);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@Valid @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.updateComment(comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteAllComments() {
        commentService.deleteAllComments();
        return ResponseEntity.noContent().build();
    }
}
