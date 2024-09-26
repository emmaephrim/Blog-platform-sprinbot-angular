package com.blog_platform.comment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment findCommentById(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    public List<Comment> findCommentsByPostId(String postId) {
        List<Comment> postComments = new ArrayList<>();
        if (postId != null) {
            List<Comment> allComments = commentRepository.findAll();
            for (Comment comment : allComments) {
                if (comment.getPostId().equals(postId)) {
                    postComments.add(comment);
                }
            }
        }
        return postComments;
    }

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public void deleteComment(String id) {
        commentRepository.deleteById(id);
    }

    public void deleteAllComments() {
        commentRepository.deleteAll();
    }

    public Comment updateComment(Comment comment) {
        return commentRepository.save(comment);
    }

}
