import { Component, OnInit, OnChanges, Input, Output, EventEmitter } from '@angular/core';
import { Income } from '../income';
import { IncomeService } from '../income.service'
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-income-form',
  templateUrl: './income-form.component.html',
  styleUrls: ['./income-form.component.css']
})
export class IncomeFormComponent implements OnInit, OnChanges {

  @Input() income: Income;
  public newIncome: Income = new Income();
  public model: Income = new Income();

  constructor(
    private incomeService: IncomeService,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit() { }

  ngOnChanges() {
    this.model = Object.assign({}, this.income);
  }

  submit(form) {
    this.newIncome.amount = this.model.amount;
    this.newIncome.note = this.model.note;
    this.incomeService.createIncome(this.newIncome);
    this.newIncome = null;
    this.router.navigate([`/transactions/${this.authService.user.userId}`]);
  }

}
