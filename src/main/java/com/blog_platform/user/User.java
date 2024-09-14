package com.blog_platform.user;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import com.blog_platform.post.Post;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Date createdAt = new Date();
    private List<Post> likedPosts;
    private List<Post> dislikedPosts;

    public User() {
    }

    public User(String id, String username, String password, String email, String role, Date createdAt,
            List<Post> likedPosts, List<Post> dislikedPosts) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.likedPosts = likedPosts;
        this.dislikedPosts = dislikedPosts;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Post> getLikedPosts() {
        return likedPosts;
    }

    public void setLikedPosts(List<Post> likedPosts) {
        this.likedPosts = likedPosts;
    }

    public List<Post> getDislikedPosts() {
        return dislikedPosts;
    }

    public void setDislikedPosts(List<Post> dislikedPosts) {
        this.dislikedPosts = dislikedPosts;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
                + role + ", createdAt=" + createdAt + ", likedPosts=" + likedPosts + ", dislikedPosts=" + dislikedPosts
                + "]";
    }

    

}
