import { Injectable } from '@angular/core';
import { User } from './user';
import { HttpHeaders, HttpClient } from '@angular/common/http';

export const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': ''
  })
};

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  public user: User;
  public redirectUrl: string;

  private userUrl: string = 'http://localhost:8080/users/create'

  constructor(private http : HttpClient) { }

  createUser(user: User): Promise<User> {
    return this.http.post<User>(`${this.userUrl}`, user, httpOptions).toPromise();
  }
}
