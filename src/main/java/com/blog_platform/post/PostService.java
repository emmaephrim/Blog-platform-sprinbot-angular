package com.blog_platform.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public void updatePost(Post post) {

        postRepository.save(post);

    }

    public void deletePost(String id) {
        postRepository.deleteById(id);
    }

    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    public Post findPostById(String id) {
        return postRepository.findById(id).orElse(null);
    }

    public Page<Post> getAllPostPageable(Pageable pageable, String search) {
        if (search != null && !search.isEmpty()) {
            List<Post> posts = new ArrayList<>();
            postRepository.findAll().forEach(post -> {
                if (post.getTitle().toLowerCase().contains(search.toLowerCase())
                        || post.getContent().toLowerCase().contains(search.toLowerCase())
                        || post.getCategoryId().contains(search)) {
                    posts.add(post);
                }
            });
            return new PageImpl<>(posts, pageable, posts.size());
        }

        return Page.empty(pageable);
    }

    public Page<Post> getAllPostPageable(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Page<Post> findPostsByCategory(String categoryId, Pageable pageable) {
        List<Post> posts = new ArrayList<>();
        postRepository.findAll().forEach(post -> {
            if (post.getCategoryId() != null && post.getCategoryId().equals(categoryId)) {
                posts.add(post);
            }
        });
        return new PageImpl<>(posts, pageable, posts.size());

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
