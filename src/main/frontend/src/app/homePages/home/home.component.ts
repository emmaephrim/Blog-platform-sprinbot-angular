import { Component, inject } from '@angular/core';
import { RouterModule, RouterOutlet } from '@angular/router';
import { PostsComponent } from '../posts/posts.component';
import { CommonModule } from '@angular/common';
import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [PostsComponent, RouterOutlet, CommonModule, RouterModule],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css', './blog-home.css'],
})
export class HomeComponent {
  currentYear = new Date().getFullYear();

  authService: AuthService = inject(AuthService);
  isBrowser = () => this.authService.isBrowser();
  isNormalUser = () => this.authService.isNormalUser();
  isAdmin = () => this.authService.isAdmin();
}
