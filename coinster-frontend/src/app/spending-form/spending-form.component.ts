import { Component, OnInit, OnChanges, Input, Output, EventEmitter } from '@angular/core';
import { Spending } from '../spending';
import { SpendingService } from '../spending.service'
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-spending-form',
  templateUrl: './spending-form.component.html',
  styleUrls: ['./spending-form.component.css']
})
export class SpendingFormComponent implements OnInit, OnChanges {

  @Input() spending: Spending;
  public newSpending: Spending = new Spending();
  public model: Spending = new Spending();

  constructor(
  private spendingService: SpendingService,
  private router: Router
  ) { }

  ngOnInit() { }

  ngOnChanges() {
    this.model = Object.assign({}, this.spending);
  }

  submit(form) {
    this.newSpending.amount = this.model.amount;
    this.newSpending.planned = this.model.planned;
    this.newSpending.category = this.model.category;
    this.newSpending.createdAt = new Date();
    this.spendingService.createSpending(this.newSpending);
    this.newSpending = null;
    this.router.navigate(['/transactions']);
  }

}
