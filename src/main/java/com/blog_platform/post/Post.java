package com.blog_platform.post;

import java.util.Date;

import org.springframework.data.annotation.Id;
// import javax.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Document(collection = "posts")
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    @NotNull
    @Size(min = 3, message = "User ID is mandatory")
    @NotBlank(message = "User ID is mandatory")
    private String userId;
    private Date createdAt;
    // private List<Comment> comments;

    public Post() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getCreatedA() {
        return createdAt;
    }

    public void setCreatedA(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId + ", createdAt="
                + createdAt + "]";
    }

    // public List<Comment> getComments() {
    // return comments;
    // }

    // public void setComments(List<Comment> comments) {
    // this.comments = comments;
    // }

}
