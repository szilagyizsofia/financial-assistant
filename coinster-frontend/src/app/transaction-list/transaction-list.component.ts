import { Component, OnInit } from '@angular/core';
import { IncomeService } from '../income.service';
import { Income } from '../income';
import { SpendingService} from '../spending.service';
import { Spending } from '../spending';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css'],
})
export class TransactionListComponent implements OnInit {

  public incomes: Income[] = [];
  public spendings: Spending[] = [];

  constructor(
    private incomeService: IncomeService,
    private spendingService: SpendingService
  ) { }

  async ngOnInit(): Promise<void> {
    this.incomes = await this.incomeService.getIncomes();
    this.spendings = await this.spendingService.getSpendings();
  }

  

}
