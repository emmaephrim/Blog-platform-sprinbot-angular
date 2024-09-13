export class CommentModel {
  id: string | null;
  content: string;
  createdAt: string;
  userId: string;
  postId: string;

  constructor() {
    this.id = '';
    this.content = '';
    this.createdAt = '';
    this.userId = '';
    this.postId = '';
  }
}
