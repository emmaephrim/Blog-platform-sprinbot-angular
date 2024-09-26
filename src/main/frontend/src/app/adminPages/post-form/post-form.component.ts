import { Component, inject } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Post } from '../../model/post';
import { PostService } from '../../service/post.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-post-form',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './post-form.component.html',
  styleUrl: './post-form.component.css',
})
export class PostFormComponent {
  postForm: FormGroup;
  post: Post = new Post();
  postService: PostService = inject(PostService);
  constructor(private route: ActivatedRoute, private router: Router) {
    this.postForm = new FormGroup({
      title: new FormControl(this.post.title, [Validators.required]),
      imageUrl: new FormControl(this.post.imageUrl, [
        Validators.required,
        Validators.pattern('https?://.+'),
      ]),
      content: new FormControl(this.post.content, [
        Validators.required,
        Validators.max(200),
      ]),
      userId: new FormControl(this.post.userId),
      createdAt: new FormControl(this.post.createdAt, [Validators.required]),
      // comments: new FormControl(this.post.comments),
      likes: new FormControl(this.post.likes),
      dislikes: new FormControl(this.post.dislikes),
      shares: new FormControl(this.post.shares),
    });
  }

  public handleSubmit() {
    this.post = this.postForm.value as Post;
    this.postService.savePost(this.post).subscribe((result) => {
      console.log(result);
      this.goToDashboard();
    });
  }

  public goToDashboard() {
    this.router.navigate(['/admin']);
  }
}
