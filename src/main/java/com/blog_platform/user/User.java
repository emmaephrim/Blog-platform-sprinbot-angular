package com.blog_platform.user;

import java.util.Date;
import java.util.List;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Date createdAt = new Date();
    private List<String> likedPostsIdList = new ArrayStack<>();
    private List<String> dislikedPostsIdList = new ArrayStack<>();

    public User() {
    }

    public User(String id, String username, String password, String email, String role, Date createdAt,
            List<String> likedPostsIdList, List<String> dislikedPostsIdList) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = createdAt;
        this.likedPostsIdList = likedPostsIdList;
        this.dislikedPostsIdList = dislikedPostsIdList;
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

    public List<String> getLikedPostsIdList() {
        return likedPostsIdList;
    }

    public void setLikedPostsIdList(List<String> likedPostsIdList) {
        this.likedPostsIdList = likedPostsIdList;
    }

    public List<String> getDislikedPostsIdList() {
        return dislikedPostsIdList;
    }

    public void setDislikedPostsIdList(List<String> dislikedPostsIdList) {
        this.dislikedPostsIdList = dislikedPostsIdList;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", role="
                + role + ", createdAt=" + createdAt + ", likedPostsIdList=" + likedPostsIdList
                + ", dislikedPostsIdList=" + dislikedPostsIdList + "]";
    }

}
