package com.blog_platform.post;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.blog_platform.auth.JwtUtil;
import com.blog_platform.user.User;
import com.blog_platform.user.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@Validated
public class PostController {
    private final PostService postService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public PostController(PostService postService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.postService = postService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
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

    @GetMapping("/posts/pageable")
    public ResponseEntity<Page<Post>> findAllPostsPageable(@RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(name = "search", required = false) String search) {
        Pageable pageable = PageRequest.of(page, size);
        if (search != null && !search.isEmpty() && !search.isBlank()) {
            Page<Post> posts = postService.getAllPostPageable(pageable, search);
            return ResponseEntity.ok(posts);
        }

        Page<Post> posts = postService.getAllPostPageable(pageable);
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable String id) {
        Post post = postService.findPostById(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/posts/{id}/like")
    public ResponseEntity<Post> likePost(@PathVariable String id, @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        if (!jwtToken.isEmpty() && !jwtUtil.tokenIsValid(jwtToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Post post = postService.findPostById(id);
        String userId = jwtUtil.extractUserId(token.substring(7));
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            postService.likePost(post, user);
        }
        return ResponseEntity.ok(post);

    }

    @PutMapping("/posts/{id}/dislike")
    public ResponseEntity<Post> dislikePost(@PathVariable String id,
            @RequestHeader("Authorization") String token) {
        String jwtToken = token.substring(7);
        if (!jwtToken.isEmpty() && !jwtUtil.tokenIsValid(jwtToken)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Post post = postService.findPostById(id);
        String userId = jwtUtil.extractUserId(token.substring(7));
        User user = userRepository.findById(userId).orElse(null);

        if (user != null) {
            postService.dislikePost(post, user);
        }
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
