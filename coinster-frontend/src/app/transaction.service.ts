import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Transaction } from './transaction';
import { AuthService, httpOptions } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  private transactionUrl = 'http://localhost:8080/transactions'

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) { }

  findAll(): Promise<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.transactionUrl}`, httpOptions).toPromise();
  }
}
