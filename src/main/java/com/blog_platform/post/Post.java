package com.blog_platform.post;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
// import javax.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.mapping.Document;

import com.blog_platform.comment.Comment;

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
    private String imageUrl;
    private Date createdAt;
    private List<Comment> comments;
    private Integer likes;
    private Integer dislikes;
    private Integer shares;
    private boolean hasLiked;
    private boolean hasDisliked;

    public Post() {
    }

    public Post(String id, String title, String content,
            @NotNull @Size(min = 3, message = "User ID is mandatory") @NotBlank(message = "User ID is mandatory") String userId,
            String imageUrl, Date createdAt, List<Comment> comments, Integer likes, Integer dislikes, Integer shares,
            boolean hasLiked, boolean hasDisliked) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.imageUrl = imageUrl;
        this.createdAt = createdAt;
        this.comments = comments;
        this.likes = likes;
        this.dislikes = dislikes;
        this.shares = shares;
        this.hasLiked = hasLiked;
        this.hasDisliked = hasDisliked;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getDislikes() {
        return dislikes;
    }

    public void setDislikes(Integer dislikes) {
        this.dislikes = dislikes;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public boolean isHasLiked() {
        return hasLiked;
    }

    public void setHasLiked(boolean hasLiked) {
        this.hasLiked = hasLiked;
    }

    public boolean isHasDisliked() {
        return hasDisliked;
    }

    public void setHasDisliked(boolean hasDisliked) {
        this.hasDisliked = hasDisliked;
    }

    @Override
    public String toString() {
        return "Post [id=" + id + ", title=" + title + ", content=" + content + ", userId=" + userId + ", imageUrl="
                + imageUrl + ", createdAt=" + createdAt + ", comments=" + comments + ", likes=" + likes + ", dislikes="
                + dislikes + ", shares=" + shares + ", hasLiked=" + hasLiked + ", hasDisliked=" + hasDisliked + "]";
    }

    // public List<Comment> getComments() {
    // return comments;
    // }

    // public void setComments(List<Comment> comments) {
    // this.comments = comments;
    // }

}
