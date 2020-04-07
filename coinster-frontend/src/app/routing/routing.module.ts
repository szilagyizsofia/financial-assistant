import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserListComponent } from '../user-list/user-list.component';
import { TransactionListComponent } from '../transaction-list/transaction-list.component';
import { UserFormComponent } from '../user-form/user-form.component';
import { LoginFormComponent } from '../login-form/login-form.component';
import { SpendingFormComponent } from '../spending-form/spending-form.component';
import { IncomeFormComponent } from '../income-form/income-form.component';
import { SpendingComponent } from '../spending/spending.component';
import { IncomeComponent } from '../income/income.component';
import { AuthGuard } from '../auth.guard';

const routes: Routes = [
  {
    path: '',
    redirectTo: '/transactions',
    pathMatch: 'full'
  },
  {
    path: 'users',
    component: UserListComponent,
    canActivate: [ AuthGuard ]
  },
  {
    path: 'transactions',
    component: TransactionListComponent,
    canActivate: [ AuthGuard ]
  },
  {
    path: 'login',
    component: LoginFormComponent
  },
  {
    path: 'register',
    component: UserFormComponent
  },
  {
    path: 'createSpending',
    component: SpendingFormComponent,
    canActivate: [ AuthGuard ]
  },
  {
    path: 'createIncome',
    component: IncomeFormComponent,
    canActivate: [ AuthGuard ]
  },
  {
    path: 'spendings/:id',
    component: SpendingComponent
  },
  {
    path: 'incomes/:id',
    component: IncomeComponent
  }
];

@NgModule({
  imports: [ RouterModule.forRoot(routes)  ],
  exports: [ RouterModule ],
  declarations: []
})
export class RoutingModule { }
