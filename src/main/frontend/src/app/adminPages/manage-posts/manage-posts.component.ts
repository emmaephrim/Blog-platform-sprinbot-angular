import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DataTablesModule } from 'angular-datatables';
import { Config } from 'datatables.net';
import { Post } from '../../model/post';
import { PostService } from '../../service/post.service';
import { CategoryService } from '../../service/category.service';
import { CategoryModel } from '../../model/category';
import { Router } from '@angular/router';

@Component({
  selector: 'app-manage-posts',
  standalone: true,
  imports: [DataTablesModule, CommonModule],
  templateUrl: './manage-posts.component.html',
  styleUrl: './manage-posts.component.css',
})
export class ManagePostsComponent implements OnInit {
  posts: Post[] = [];
  categories: CategoryModel[] = [];

  dtOptions: Config = {};

  constructor(
    private postService: PostService,
    private categoryService: CategoryService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.postService.findAllPosts().subscribe((result) => {
      this.posts = result;
    });

    this.categoryService
      .getAllCategories()
      .subscribe((res) => (this.categories = res));
  }

  getPostCategory(categoryId: string) {
    const category = this.categories.find((cat) => cat.id === categoryId);
    return category ? category.name : 'Unknown';
  }

  editPost(postId: string) {
    console.log('Edit post', postId);
  }

  deletePost(postId: string) {
    if (!confirm('Are you sure you want to delete this post?')) {
      return;
    }

    this.postService.deletePost(postId).subscribe((res) => {
      alert('Post deleted successfully');
      this.posts = this.posts.filter((post) => post.id !== postId);
    });
  }

  viewPost(postId: string) {
    this.router.navigate(['/posts', postId]);
  }
}
