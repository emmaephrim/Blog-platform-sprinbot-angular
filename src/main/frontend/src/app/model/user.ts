import { Post } from './post';

export class User {
  id: string | undefined;
  username: string;
  password: string;
  email: string;
  role: string | undefined;
  createdAt: string | undefined;
  likedPosts: Post[];
  dislikedPosts: Post[];

  constructor() {
    this.username = '';
    this.password = '';
    this.email = '';
    this.likedPosts = [];
    this.dislikedPosts = [];
  }
}
