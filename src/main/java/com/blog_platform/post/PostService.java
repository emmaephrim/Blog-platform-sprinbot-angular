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

}
