package com.blog_platform.post;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

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

    @GetMapping("/posts/{id}/like")
    public ResponseEntity<Post> likePost(@PathVariable String id) {
        Post post = postService.findPostById(id);
        postService.likePost(post);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/{id}/dislike")
    public ResponseEntity<Post> dislikePost(@PathVariable String id) {
        Post post = postService.findPostById(id);
        postService.dislikePost(post);
        return ResponseEntity.ok(post);
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
