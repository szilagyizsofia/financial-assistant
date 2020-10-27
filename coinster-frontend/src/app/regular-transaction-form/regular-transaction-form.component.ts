import { Component, OnInit, OnChanges, Input, Output, EventEmitter } from '@angular/core';
import { RegularTransaction } from '../regular-transaction';
import { RegularTransactionService } from '../regular-transaction.service'
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-regular-transaction-form',
  templateUrl: './regular-transaction-form.component.html',
  styleUrls: ['./regular-transaction-form.component.css']
})
export class RegularTransactionFormComponent implements OnInit, OnChanges {

  @Input() regularTransaction: RegularTransaction;
  public newRegularTransaction: RegularTransaction = new RegularTransaction();
  public model: RegularTransaction = new RegularTransaction();
  public spending: string;

  constructor(
    private regularTransactionService: RegularTransactionService,
    private router: Router,
    private authService: AuthService
  ) { }

  ngOnInit(): void {
  }

  ngOnChanges() {
    this.model = Object.assign({}, this.regularTransaction);
  }

  submit(form) {
    if (this.spending == "false") {
      this.newRegularTransaction.amount = this.model.amount;
    } else {
      this.newRegularTransaction.amount = this.model.amount * -1;
    }
    this.newRegularTransaction.note = this.model.note;
    this.regularTransactionService.createRegularTransaction(this.newRegularTransaction);
    this.newRegularTransaction = null;
    this.router.navigate([`/profile`]);
  }

}
