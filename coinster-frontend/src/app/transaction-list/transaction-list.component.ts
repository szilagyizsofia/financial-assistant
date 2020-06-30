import { Component, OnInit } from '@angular/core';
import { IncomeService } from '../income.service';
import { Income } from '../income';
import { SpendingService} from '../spending.service';
import { Spending } from '../spending';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css'],
})
export class TransactionListComponent implements OnInit {

  public incomes: Income[] = [];
  public spendings: Spending[] = [];
  userId: number;

  constructor(
    private incomeService: IncomeService,
    private spendingService: SpendingService,
    private route: ActivatedRoute
  ) { }

  async ngOnInit(): Promise<void> {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userId = id;
    this.incomes = await this.incomeService.getIncomes(this.userId);
    this.spendings = await this.spendingService.getSpendings(this.userId);
  }

  

}
