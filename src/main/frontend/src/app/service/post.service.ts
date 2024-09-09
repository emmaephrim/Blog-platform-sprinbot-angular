import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Post } from '../model/post';

@Injectable({
  providedIn: 'root',
})
export class PostService {
  private baseUrl: string;
  constructor(private http: HttpClient) {
    this.baseUrl = 'http://localhost:8080/api/posts';
  }

  public findAllPosts(): Observable<Post[]> {
    return this.http.get<Post[]>(this.baseUrl);
  }

  public savePost(post: Post): Observable<Post> {
    return this.http.post<Post>(this.baseUrl, post);
  }
  // getPosts() {
  //   const headers = new HttpHeaders().set('Authorization', `Bearer ${this.authService.getToken()}`);
  //   return this.http.get(this.apiUrl, { headers });
  // }
}
