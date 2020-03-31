import { Injectable } from '@angular/core';
import { Income } from './income';
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
export class IncomeService {
  public income: Income;
  public redirectUrl: string;

  private incomeUrl: string = 'http://localhost:8080/incomes/create'

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    httpOptions.headers = httpOptions.headers.set('Authorization', `Basic ${authService.getToken()}`);
  }

  createIncome(income: Income): Promise<Income> {
    return this.http.post<Income>(`${this.incomeUrl}`, income, httpOptions).toPromise();
  }
}
