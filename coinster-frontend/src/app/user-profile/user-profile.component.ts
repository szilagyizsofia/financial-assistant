import { Component, OnInit } from '@angular/core';
import { RegularTransactionService } from '../regular-transaction.service';
import { AuthService} from '../auth.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css'],
})


export class UserProfileComponent implements OnInit {


  constructor(
  public authService: AuthService
  ) { }

  async ngOnInit(): Promise<void> {
  }

  

}
