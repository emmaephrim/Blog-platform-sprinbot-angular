import { CommonModule, NgFor } from '@angular/common';
import { Component, inject } from '@angular/core';
import { Post } from '../../model/post';
import { PostService } from '../../service/post.service';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-posts',
  standalone: true,
  imports: [CommonModule, NgFor, RouterLink],
  templateUrl: './posts.component.html',
  styleUrl: './posts.component.css',
})
export class PostsComponent {
  posts: Post[] = [];
  postService: PostService = inject(PostService);

  constructor() {
    this.postService.findAllPosts().subscribe((data) => {
      this.posts = data;
    });
  }

  formatDate(date: string): string {
    const formattedDate = new Date(date).toUTCString().slice(0, 16);
    return formattedDate;
  }

  ngOnInit() {
    this.postService.findAllPosts().subscribe((data) => {
      this.posts = data;
    });
  }

  sharePost(postId: string | null) {
    // const postUrl = `http://yourblog.com/posts/${postId}`;
    const postUrl = `/`;
    navigator.clipboard.writeText(postUrl);
    alert('Post URL copied to clipboard!');
  }

  shareCount = 5;

  likePost(postId: string | null) {
    for (let i = 0; i < this.posts.length; i++) {
      if (this.posts[i].id === postId) {
        // Check if the user has already liked the post
        if (!this.posts[i].hasLiked) {
          this.posts[i].likes++; // Increase likes

          // If the user previously disliked, remove the dislike
          if (this.posts[i].hasDisliked) {
            this.posts[i].dislikes--;
            this.posts[i].hasDisliked = false; // Reset dislike state
          }

          this.posts[i].hasLiked = true; // Set like state
        } else {
          // If already liked, remove the like (unlike)
          this.posts[i].likes--;
          this.posts[i].hasLiked = false;
        }
      }
    }
  }

  dislikePost(postId: string | null) {
    for (let i = 0; i < this.posts.length; i++) {
      if (this.posts[i].id === postId) {
        // Check if the user has already disliked the post
        if (!this.posts[i].hasDisliked) {
          this.posts[i].dislikes++; // Increase dislikes

          // If the user previously liked, remove the like
          if (this.posts[i].hasLiked) {
            this.posts[i].likes--;
            this.posts[i].hasLiked = false; // Reset like state
          }

          this.posts[i].hasDisliked = true; // Set dislike state
        } else {
          // If already disliked, remove the dislike (undislike)
          this.posts[i].dislikes--;
          this.posts[i].hasDisliked = false;
        }
      }
    }
  }
}
