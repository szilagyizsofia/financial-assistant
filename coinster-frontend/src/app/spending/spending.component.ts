import { Component, OnInit } from '@angular/core';
import { Spending } from '../spending';
import { ActivatedRoute } from '@angular/router';
import { SpendingService } from '../spending.service';

@Component({
  selector: 'app-spending',
  templateUrl: './spending.component.html',
  styleUrls: ['./spending.component.css']
})
export class SpendingComponent implements OnInit {

  public spending: Spending = null;

  constructor(
    private route: ActivatedRoute,
    private spendingService: SpendingService
  ) { }

  async ngOnInit(): Promise<void> {
    const id = +this.route.snapshot.paramMap.get('id');
    this.spending = await this.spendingService.getSpending(id);
  }

}
