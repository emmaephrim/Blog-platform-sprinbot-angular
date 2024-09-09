import { Component } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { PostsComponent } from '../posts/posts.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [PostsComponent, RouterOutlet, CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css', './blog-home.css'],
})
export class HomeComponent {}
