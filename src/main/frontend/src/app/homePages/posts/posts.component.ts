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
    this.postService.likePost(postId as string).subscribe((updatedPost) => {
      const post = this.posts.find((p) => (p.id = updatedPost.id));
      if (post) {
        post.likes = updatedPost.likes;
        post.dislikes = updatedPost.dislikes;
        post.hasLiked = !post.hasLiked;
      }
    });
  }

  dislikePost(postId: string | null) {
    this.postService.dislikePost(postId as string).subscribe((updatedPost) => {
      const post = this.posts.find((p) => p.id == updatedPost.id);
      if (post) {
        post.likes = updatedPost.likes;
        post.dislikes = updatedPost.dislikes;
        post.hasDisliked = !post.hasDisliked;
      }
    });
  }
}
