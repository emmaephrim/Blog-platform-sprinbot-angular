package com.blog_platform.post;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public void createPost(@Validated Post postDto) {
        postRepository.save(postDto);
    }

    public void updatePost(Post postDto) {
    }

    public void deletePost(Long postId) {
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post findPostById(String id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post likePost(Post post) {
        post.setLikes(post.getLikes() + 1);
        postRepository.save(post);
        return post;

    }

    public Post dislikePost(Post post) {
        post.setDislikes(post.getDislikes() + 1);
        postRepository.save(post);
        return post;
    }

}
