import { Component, OnInit } from '@angular/core';
import { Income } from '../income';
import { ActivatedRoute } from '@angular/router';
import { IncomeService } from '../income.service';

@Component({
  selector: 'app-income',
  templateUrl: './income.component.html',
  styleUrls: ['./income.component.css']
})
export class IncomeComponent implements OnInit {

  public income: Income = null;

  constructor(
    private route: ActivatedRoute,
    private incomeService: IncomeService
  ) { }

  async ngOnInit(): Promise<void> {
    const id = +this.route.snapshot.paramMap.get('id');
    this.income = await this.incomeService.getIncome(id);
  }

}
