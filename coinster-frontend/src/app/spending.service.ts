import { Injectable } from '@angular/core';
import { Spending } from './spending';
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
export class SpendingService {
  public spending: Spending;
  public redirectUrl: string;

  private spendingUrl: string = 'http://localhost:8080/spendings'

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {
    httpOptions.headers = httpOptions.headers.set('Authorization', `Basic ${authService.getToken()}`);
  }

  createSpending(spending: Spending): Promise<Spending> {
    return this.http.post<Spending>(`${this.spendingUrl}`, spending, httpOptions).toPromise();
  }

  deleteSpending(id): Promise<Spending> {
    return this.http.delete<Spending>(`${this.spendingUrl}/${id}`, httpOptions).toPromise();
  }

  getSpendings(): Promise<Spending[]> {
    return this.http.get<Spending[]>(`${this.spendingUrl}/findByOwner/${this.authService.user.userId}`, httpOptions).toPromise();
  }

  getSpending(id: number): Promise<Spending> {
    return this.http.get<Spending>(`${this.spendingUrl}/${id}`, httpOptions).toPromise();
  }

  getThisMonthSpendingSum(): Promise<number> {
    return this.http.get<number>(`${this.spendingUrl}/thisMonthSum/${this.authService.user.userId}`, httpOptions).toPromise();
  }

  getThisMonthSpendingByCategory(category: string): Promise<number> {
    return this.http.get<number>(`${this.spendingUrl}/thisMonthSumByCategory/${this.authService.user.userId}/${category}`, httpOptions).toPromise();
  }

  getThisMontSpendingByPlanned(planned: boolean): Promise<number> {
    return this.http.get<number>(`${this.spendingUrl}/thisMonthSumByPlanned/${this.authService.user.userId}/${planned}`, httpOptions).toPromise();
  }

}
