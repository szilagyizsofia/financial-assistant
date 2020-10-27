import { Component, OnInit } from '@angular/core';
import { RegularTransactionService } from '../regular-transaction.service';
import { RegularTransaction } from '../regular-transaction';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})


export class UserProfileComponent implements OnInit {

  public regularTransactions: RegularTransaction[] = [];
  userId: number;

  constructor(
    public authService: AuthService,
    private regularTransactionService: RegularTransactionService,
    private route: ActivatedRoute
  ) { }

  async ngOnInit(): Promise<void> {
    this.userId = this.authService.user.userId;
    this.regularTransactions = await this.regularTransactionService.getRegularTransactions(this.userId);
  }

}
