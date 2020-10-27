import { Component, OnInit } from '@angular/core';
import { RegularTransaction } from '../regular-transaction';
import { ActivatedRoute } from '@angular/router';
import { RegularTransactionService } from '../regular-transaction.service';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-regular-transaction',
  templateUrl: './regular-transaction.component.html',
  styleUrls: ['./regular-transaction.component.css']
})
export class RegularTransactionComponent implements OnInit {

  public regularTransaction: RegularTransaction = null;

  constructor(
    public authService: AuthService,
    private route: ActivatedRoute,
    private regularTransactionService: RegularTransactionService
  ) { }

  async ngOnInit(): Promise<void> {
    const id = +this.route.snapshot.paramMap.get('id');
    this.regularTransaction = await this.regularTransactionService.getRegularTransaction(id);
  }

}
