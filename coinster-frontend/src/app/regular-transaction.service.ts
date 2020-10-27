import { Injectable } from '@angular/core';
import { RegularTransaction } from './regular-transaction';
import { AuthService } from './auth.service'
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
export class RegularTransactionService {
  public regularTransaction: RegularTransaction;
  public redirectUrl: string;

  private regularTransactionUrl: string = 'http://localhost:8080/regulars'

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    httpOptions.headers = httpOptions.headers.set('Authorization', `Basic ${authService.getToken()}`);
  }

  createRegularTransaction(regularTransaction: RegularTransaction): Promise<RegularTransaction> {
    return this.http.post<RegularTransaction>(`${this.regularTransactionUrl}`, regularTransaction, httpOptions).toPromise();
  }

  getRegularTransactions(userId: number): Promise<RegularTransaction[]> {
    console.log(userId);
    return this.http.get<RegularTransaction[]>(`${this.regularTransactionUrl}/findByOwner/${userId}`, httpOptions).toPromise();
  }

  getRegularTransaction(id: number): Promise<RegularTransaction> {
    return this.http.get<RegularTransaction>(`${this.regularTransactionUrl}/${id}`, httpOptions).toPromise();
  }
}
