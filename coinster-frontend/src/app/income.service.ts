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

  private incomeUrl: string = 'http://localhost:8080/incomes'

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    httpOptions.headers = httpOptions.headers.set('Authorization', `Basic ${authService.getToken()}`);
  }

  createIncome(income: Income): Promise<Income> {
    return this.http.post<Income>(`${this.incomeUrl}`, income, httpOptions).toPromise();
  }

  deleteIncome(id): Promise<Income> {
    return this.http.delete<Income>(`${this.incomeUrl}/${id}`, httpOptions).toPromise();
  }

  getIncomes(): Promise<Income[]> {
    return this.http.get<Income[]>(`${this.incomeUrl}/findByOwner/${this.authService.user.userId}`, httpOptions).toPromise();
  }

  getIncome(id: number): Promise<Income> {
    return this.http.get<Income>(`${this.incomeUrl}/${id}`, httpOptions).toPromise();
  }

  getThisMonthIncomeSum(): Promise<number> {
    return this.http.get<number>(`${this.incomeUrl}/thisMonthSum/${this.authService.user.userId}`, httpOptions).toPromise();
  }
}
