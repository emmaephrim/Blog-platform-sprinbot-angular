package com.blog_platform.post;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
@Validated
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post) {
        postService.createPost(post);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts")
    public List<Post> findAllPosts() {
        return postService.getAllPost();
    }

    // public void updatePost(Post postDto) {
    // postService.updatePost(postDto);
    // }

    // public void deletePost(Long postId) {
    // postService.deletePost(postId);
    // }

    // public Post getPost(Long postId) {
    // return postService.getPost(postId);
    // }

}
