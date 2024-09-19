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
    const post = this.posts.find((p) => p.id === postId);
    if (post) {
      post.hasLiked = !post.hasLiked;
      if (post.hasLiked) {
        post.likes += 1;
      } else {
        post.likes -= 1;
      }

      this.postService.likePost(postId as string).subscribe(
        (updatedPost) => {
          const index = this.posts.findIndex((p) => p.id === updatedPost.id);
          if (index !== -1) {
            this.posts[index] = updatedPost;
          }
        },
        (error) => {
          alert('Error liking post!');
          post.hasLiked = !post.hasLiked;
          if (post.hasLiked) {
            post.likes += 1;
          } else {
            post.likes -= 1;
          }
        }
      );
    }
  }

  dislikePost(postId: string | null) {
    const post = this.posts.find((p) => p.id === postId);
    if (post) {
      post.hasDisliked = !post.hasDisliked;
      if (post.hasDisliked) {
        post.dislikes += 1;
      } else {
        post.dislikes -= 1;
      }

      this.postService.dislikePost(postId as string).subscribe(
        (updatedPost) => {
          const index = this.posts.findIndex((p) => p.id === updatedPost.id);
          if (index !== -1) {
            this.posts[index] = updatedPost;
          }
        },
        (error) => {
          alert('Error disliking post!');
          post.hasDisliked = !post.hasDisliked;
          if (post.hasDisliked) {
            post.dislikes += 1;
          } else {
            post.dislikes -= 1;
          }
        }
      );
    }
  }

  getPostAuthor(postId: string) {
    const post = this.posts.find((p) => p.id == postId);
    return post?.userId;
  }
}
