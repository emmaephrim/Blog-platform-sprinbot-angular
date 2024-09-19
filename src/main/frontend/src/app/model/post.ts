import { inject } from '@angular/core';
import { AuthService } from '../service/auth.service';

export class Post {
  authService: AuthService = inject(AuthService);
  id: string | null;
  title: string;
  content: string;
  createdAt: string;
  userId: string;
  imageUrl: string;
  comments: string[];
  likes: number;
  dislikes: number;
  shares: number;
  hasLiked: boolean;
  hasDisliked: boolean;

  constructor() {
    this.id = '';
    this.title = '';
    this.content = '';
    this.createdAt = '';
    this.userId = this.authService.getUserId();
    this.imageUrl = '';
    this.comments = [];
    this.likes = 0;
    this.dislikes = 0;
    this.shares = 0;
    this.hasLiked = false;
    this.hasDisliked = false;
  }
}
