package com.blog_platform.post;

import java.util.List;
import java.util.ArrayList;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import com.blog_platform.user.User;
import com.blog_platform.user.UserRepository;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
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

    @Transactional
    public Post likePost(Post post, User user) {
        if (user.getLikedPostsIdList() == null) {
            user.setLikedPostsIdList(new ArrayList<>());
        }
        if (user.getDislikedPostsIdList() == null) {
            user.setDislikedPostsIdList(new ArrayList<>());
        }

        // If the post is already disliked, remove it from the disliked list and
        // decrease dislikes count
        if (user.getDislikedPostsIdList().contains(post.getId())) {
            user.getDislikedPostsIdList().remove(post.getId());
            if (post.getDislikes() == null) {
                post.setDislikes(0);
            } else {
                post.setDislikes(post.getDislikes() - 1);
            }
        }

        // If the post is not already liked, like it
        if (!user.getLikedPostsIdList().contains(post.getId())) {
            user.getLikedPostsIdList().add(post.getId());
            if (post.getLikes() == null) {
                post.setLikes(1);
            } else {
                post.setLikes(post.getLikes() + 1);
            }
        }

        // Save the updated user and post data
        userRepository.save(user);
        postRepository.save(post);
        return post;

    }

    @Transactional
    public Post dislikePost(Post post, User user) {
        if (user.getDislikedPostsIdList() == null) {
            user.setDislikedPostsIdList(new ArrayList<>());
        }
        if (user.getLikedPostsIdList() == null) {
            user.setLikedPostsIdList(new ArrayList<>());
        }

        // If the post is already liked, remove it from the liked list and decrease
        // likes count
        if (user.getLikedPostsIdList().contains(post.getId())) {
            user.getLikedPostsIdList().remove(post.getId());
            if (post.getLikes() == null) {
                post.setLikes(0);
            }
            {
                post.setLikes(post.getLikes() - 1);
            }
        }

        // If the post is not already disliked, dislike it
        if (!user.getDislikedPostsIdList().contains(post.getId())) {
            user.getDislikedPostsIdList().add(post.getId());
            if (post.getDislikes() == null) {
                post.setDislikes(1);
            } else {
                post.setDislikes(post.getDislikes() + 1);
            }
        }

        // Save the updated user and post data
        userRepository.save(user);
        postRepository.save(post);
        return post;
    }

}
