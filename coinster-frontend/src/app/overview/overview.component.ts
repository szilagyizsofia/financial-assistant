import { Component, OnInit } from '@angular/core';
import { Chart } from 'node_modules/chart.js';
import { SpendingService } from '../spending.service';
import { IncomeService } from '../income.service';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-overview',
  templateUrl: './overview.component.html',
  styleUrls: ['./overview.component.css']
})
export class OverviewComponent implements OnInit {

  userId: number;
  public spendingSum: number;
  public incomeSum: number;

  constructor(
    private spendingService: SpendingService,
    private incomeService: IncomeService,
    private route: ActivatedRoute,
    public authService: AuthService
  ) { }

  async ngOnInit(): Promise<void> {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userId = id;
    this.spendingSum = await this.spendingService.getThisMonthSpendingSum(this.userId) * -1;
    this.incomeSum = await this.incomeService.getThisMonthIncomeSum(this.userId);
    this.drawChart();
  }

  drawChart(): void {
    new Chart(document.getElementById("pie-chart"), {
    type: 'pie',
    data: {
      labels: ["Income", "Spending"],
      datasets: [{
        label: "Money",
        backgroundColor: ["#3e95cd", "#8e5ea2"],
        data: [this.incomeSum, this.spendingSum]
      }]
    },
    options: {
      title: {
        display: true,
        text: 'This month\'s income-spending ratio'
      }
    }
});
  }

}
