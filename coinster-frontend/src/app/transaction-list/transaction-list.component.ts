import { Component, OnInit } from '@angular/core';
import { TransactionService} from '../transaction.service';
import { Transaction } from '../transaction'

@Component({
  selector: 'app-transaction-list',
  templateUrl: './transaction-list.component.html',
  styleUrls: ['./transaction-list.component.css'],
})
export class TransactionListComponent implements OnInit {

  public transactions: Transaction[] = [];

  constructor(private transactionService: TransactionService) { }

  async ngOnInit(): Promise<void> {
    this.transactions = await this.transactionService.findAll();
  }

  

}
