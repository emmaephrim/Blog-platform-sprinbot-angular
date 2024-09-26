import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../model/post';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private baseUrl: string;
  authService: AuthService = inject(AuthService);
  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/api/posts';
    // this.baseUrl = 'https://blog-app-v1-tagname.onrender.com/api/posts';
  }

  public findAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl);
  }

  public findPostById(postId: string): Observable<Post> {
    return this.http.get<Post>(`${this.baseUrl}/${postId}`);
  }

  public savePost(post: Post): Observable<Post> {
    console.log('this post: ', post);
    return this.http.post<Post>(this.baseUrl, post);
  }

  public findPostsPageable(
    page: number,
    size: number,
    search: string = ''
  ): Observable<Post[]> {
    return this.http.get<Post[]>(
      `${this.baseUrl}/pageable?page=${page}&size=${size}&search=${search}`
    );
  }

  // Like a post
  likePost(postId: string): Observable<Post> {
    const headers = new HttpHeaders().set(
      'Authorization',
      `Bearer ${this.authService.getToken()}`
    );

    return this.http.put<Post>(
      `${this.baseUrl}/${postId}/like`,
      {},
      { headers }
    );
  }

  // Dislike a post
  dislikePost(postId: string): Observable<Post> {
    const headers = new HttpHeaders().set(
      'Authorization',
      `Bearer ${this.authService.getToken()}`
    );
    return this.http.put<Post>(
      `${this.baseUrl}/${postId}/dislike`,
      {},
      {
        headers,
      }
    );
  }

  // getPosts() {
  //   const headers = new HttpHeaders({
  //     Authorization: `Bearer ${this.getToken()}`,  // Attach JWTtoken
  //   });
  //   return this.http.get(this.apiUrl, { headers });
  // }
}
