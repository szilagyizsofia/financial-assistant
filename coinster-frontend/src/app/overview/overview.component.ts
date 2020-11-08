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
  public groceriesSum: number;
  public rentSum: number;
  public billsSum: number;
  public eatingoutSum: number;
  public fashionSum: number;
  public electronicsSum: number;
  public funSum: number;
  public decorSum: number;
  public plannedSum: number;
  public impulsiveSum: number;

  constructor(
    private spendingService: SpendingService,
    private incomeService: IncomeService,
    private route: ActivatedRoute,
    public authService: AuthService
  ) { }

  async ngOnInit(): Promise<void> {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userId = id;
    this.spendingSum = await this.spendingService.getThisMonthSpendingSum() * -1;
    this.incomeSum = await this.incomeService.getThisMonthIncomeSum();
    this.groceriesSum = await this.spendingService.getThisMonthSpendingByCategory("groceries");
    this.rentSum = await this.spendingService.getThisMonthSpendingByCategory("rent");
    this.billsSum = await this.spendingService.getThisMonthSpendingByCategory("bills");
    this.eatingoutSum = await this.spendingService.getThisMonthSpendingByCategory("eatingout");
    this.fashionSum = await this.spendingService.getThisMonthSpendingByCategory("fashion");
    this.electronicsSum = await this.spendingService.getThisMonthSpendingByCategory("electronics");
    this.funSum = await this.spendingService.getThisMonthSpendingByCategory("fun");
    this.decorSum = await this.spendingService.getThisMonthSpendingByCategory("decor");
    this.plannedSum = await this.spendingService.getThisMontSpendingByPlanned(true);
    this.impulsiveSum = await this.spendingService.getThisMontSpendingByPlanned(false);
    this.drawSpendingIncomeRatioChart();
    this.drawSpendingCategoryRatioChart();
    this.drawSpendingPlannedChart();
  }

  drawSpendingIncomeRatioChart(): void {
    new Chart(document.getElementById("spending-income-pie-chart"), {
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

  drawSpendingCategoryRatioChart(): void {
    new Chart(document.getElementById("spending-categories-pie-chart"), {
    type: 'pie',
    data: {
      labels: ["Groceries", "Rent", "Bills", "Eating out", "Fashion", "Electronics", "Fun", "Decor"],
      datasets: [{
        label: "Money",
        backgroundColor: ["#622569", "#b8a9c9", "#d6d4e0", "#5b9aa0", "#c83349", "#e06377", "#eeac99", "#f9d5e5"],
        data: [this.groceriesSum, this.rentSum, this.billsSum, this.eatingoutSum, this.fashionSum, this.electronicsSum, this.funSum, this.decorSum]
      }]
    },
    options: {
      title: {
        display: true,
        text: 'This month\'s spendings by category'
      }
    }
    });
  }

  drawSpendingPlannedChart(): void {
    new Chart(document.getElementById("spending-planned-pie-chart"), {
    type: 'pie',
    data: {
      labels: ["Planned", "Impulsive"],
      datasets: [{
        label: "Money",
        backgroundColor: ["#539da2", "#244852"],
        data: [this.plannedSum, this.impulsiveSum]
      }]
    },
    options: {
      title: {
        display: true,
        text: 'This month\'s planned-impulsive ratio'
      }
    }
    });
  }
}
