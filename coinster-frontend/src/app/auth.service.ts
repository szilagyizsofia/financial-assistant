import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

export const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': ''
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public isLoggedIn: boolean = false;
  public user: User;
  public redirectUrl: string;
  public token: string;

  private authUrl: string = 'http://localhost:8080/users';
  private logoutUrl: string = 'http://localhost:8080/users/logout';

  constructor(
    private http: HttpClient,
    private router: Router
    ) { }

  async login(username: string, password: string): Promise<User> {
    try {
      this.token = btoa(`${username}:${password}`);
      httpOptions.headers = httpOptions.headers.set('Authorization', `Basic ${this.token}`);
      this.user = await this.http.post<User>(`${this.authUrl}/login`, {}, httpOptions).toPromise();
      this.isLoggedIn = true;
      console.log(this.user);
      return Promise.resolve(this.user);
    } catch (e) {
      console.log(e);
      return Promise.reject();
      }
  }

  async logout() {
    httpOptions.headers = httpOptions.headers.set('Authorization', ``);
    this.isLoggedIn = false;
    this.user = null;
    this.router.navigate(['/login']);
  }

  getToken() {
    return this.token;
  }
}
