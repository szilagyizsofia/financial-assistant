import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {User} from './user';
import { AuthService, httpOptions } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private userUrl = 'http://localhost:8080/users'

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  findAll(): Promise<User[]> {
    return this.http.get<User[]>(`${this.userUrl}`, httpOptions).toPromise();
  }

  deleteUser(id): Promise<User> {
    return this.http.delete<User>(`${this.userUrl}/${id}`, httpOptions).toPromise();
  }

  changePlan(id): Promise<User> {
    return this.http.get<User>(`${this.userUrl}/changePlan/${id}`, httpOptions).toPromise();
  }
}
