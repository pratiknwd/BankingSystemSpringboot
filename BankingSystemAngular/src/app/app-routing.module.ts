import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllToolsComponent } from './Component/FinancialTools/all-tools/all-tools.component';
import { CiCalculatorComponent } from './Component/FinancialTools/ci-calculator/ci-calculator.component';
import { GstCalculatorComponent } from './Component/FinancialTools/gst-calculator/gst-calculator.component';
import { PpfCalculatorComponent } from './Component/FinancialTools/ppf-calculator/ppf-calculator.component';
import { SipCalculatorComponent } from './Component/FinancialTools/sip-calculator/sip-calculator.component';
import { AccountBalanceComponent } from './Component/account-balance/account-balance.component';
import { AccountComponent } from './Component/application/account/account.component';
import { ApplicationComponent } from './Component/application/application.component';
import { LoanAndCredtCardComponent } from './Component/application/loan-and-credt-card/loan-and-credt-card.component';
import { ViewApplicationComponent } from './Component/application/view-application/view-application.component';
import { AuthFormComponent } from './Component/auth-form/auth-form.component';
import { AddBeneficiariesComponent } from './Component/beneficiaries/add-beneficiaries/add-beneficiaries.component';
import { ShowBeneficiariesComponent } from './Component/beneficiaries/show-beneficiaries/show-beneficiaries.component';
import { CardsComponent } from './Component/cards/cards.component';
import { ChangePinComponent } from './Component/change-pin/change-pin.component';
import { CustomerComponent } from './Component/customer/customer.component';
import { EditCustomerComponent } from './Component/edit-customer/edit-customer.component';
import { ForgotPasswordComponent } from './Component/forgot-password/forgot-password.component';
import { HomeComponent } from './Component/home/home.component';
import { InvalidUrlComponent } from './Component/invalid-url/invalid-url.component';
import { MoneyTransferComponent } from './Component/money-transfer/money-transfer.component';
import { ProfileComponent } from './Component/profile/profile.component';
import { SettingsComponent } from './Component/settings/settings.component';
import { ShowIfscComponent } from './Component/show-ifsc/show-ifsc.component';
import { StatementsComponent } from './Component/statements/statements.component';
import { UserlayoutComponent } from './Component/userlayout/userlayout.component';
import { routeGuardGuard } from './RouteGuard/route-guard.guard';

const routes: Routes = [
  {
    path: 'auth',
    component: AuthFormComponent,
  },
  {
    path: 'resetPassword',
    component: ForgotPasswordComponent,
  },
  {
    path: '',
    component: UserlayoutComponent,
    children: [
      {
        path: 'home',
        component: HomeComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'application',
        component: ApplicationComponent,
        canActivate: [routeGuardGuard],
        children: [
          { path: 'apply', component: LoanAndCredtCardComponent },
          { path: 'account', component: AccountComponent },
          { path: 'view', component: ViewApplicationComponent },
        ],
      },
      {
        path: 'branches',
        component: ShowIfscComponent,
      },
      {
        path: 'beneficiaries',
        canActivate: [routeGuardGuard],
        children: [
          {
            path: 'add',
            component: AddBeneficiariesComponent,
          },
          {
            path: 'view',
            component: ShowBeneficiariesComponent,
          },
        ],
      },
      {
        path: 'financialtools',
        component: AllToolsComponent,
      },
      {
        path: 'financialtools',
        children: [
          {
            path: 'ppfCalculator',
            component: PpfCalculatorComponent,
          },
          {
            path: 'sipCalculator',
            component: SipCalculatorComponent,
          },
          {
            path: 'ciCalculator',
            component: CiCalculatorComponent,
          },
          {
            path: 'gstCalculator',
            component: GstCalculatorComponent,
          },
        ],
      },
      {
        path: 'cards',
        component: CardsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'accountBalance',
        component: AccountBalanceComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'settings',
        component: SettingsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'change-debitCard-pin',
        component: ChangePinComponent,
        pathMatch: 'full',
        canActivate: [routeGuardGuard],
      },
      {
        path: 'change-creditCard-pin',
        component: ChangePinComponent,
        pathMatch: 'full',
        canActivate: [routeGuardGuard],
      },
      {
        path: 'change-tpin',
        component: ChangePinComponent,
        pathMatch: 'full',
        canActivate: [routeGuardGuard],
      },
      {
        path: 'moneyTransfer',
        component: MoneyTransferComponent,
        pathMatch: 'full',
        canActivate: [routeGuardGuard],
      },
      {
        path: 'accountStatement',
        component: StatementsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'registerCustomer',
        component: CustomerComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'updateDetails',
        component: EditCustomerComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'profile',
        component: ProfileComponent,
        canActivate: [routeGuardGuard],
      },
    ],
  },
  {
    path: 'admin',
    loadChildren: () =>
      import('./Component/admin/admin.module').then((m) => m.AdminModule),
  },
  {
    path: '**',
    component: InvalidUrlComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
