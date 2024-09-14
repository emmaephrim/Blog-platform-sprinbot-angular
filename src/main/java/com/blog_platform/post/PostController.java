package com.blog_platform.post;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blog_platform.user.User;
import com.blog_platform.user.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class PostController {
    private final PostService postService;
    private final UserRepository userRepository;

    public PostController(PostService postService, UserRepository userRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
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

    @PutMapping("/posts/{id}/like")
    public ResponseEntity<Post> likePost(@PathVariable String id, @RequestHeader("userId") String userId) {
        Post post = postService.findPostById(id);
        User user = userRepository.findById(userId).orElse(null);

        if (user != null && user.getLikedPosts().contains(post)) {
            postService.dislikePost(post);
            return ResponseEntity.ok(post);
        } else {
            postService.likePost(post);
            return ResponseEntity.ok(post);
        }

    }

    @PutMapping("/posts/{id}/dislike")
    public ResponseEntity<Post> dislikePost(@PathVariable String id, @RequestHeader("userId") String userId) {
        Post post = postService.findPostById(id);
        User user = userRepository.findById(userId).orElse(null);

        if (user != null && user.getDislikedPosts().contains(post)) {
            postService.likePost(post);
            return ResponseEntity.ok(post);
        } else {
            postService.dislikePost(post);
            return ResponseEntity.ok(post);
        }

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
