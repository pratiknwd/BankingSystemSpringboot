import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { routeGuardGuard } from 'src/app/RouteGuard/route-guard.guard';
import { AccountFunctionalitiesComponent } from './account-functionalities/account-functionalities.component';
import { AddBranchComponent } from './add-branch/add-branch.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';
import { AllBankComponent } from './all-bank/all-bank.component';
import { ApplicationTypeComponent } from './application-type/application-type.component';
import { BeneficiaryApplicationComponent } from './beneficiary-application/beneficiary-application.component';
import { BlockUnblockAccountsComponent } from './block-unblock-accounts/block-unblock-accounts.component';
import { BlockedAccountsComponent } from './blocked-accounts/blocked-accounts.component';
import { BranchDetailComponent } from './branch-detail/branch-detail.component';
import { CardControllerComponent } from './card-controller/card-controller.component';
import { DocumentTypesComponent } from './document-types/document-types.component';
import { LoanTypeComponent } from './loan-type/loan-type.component';
import { LoginSignupComponent } from './login-signup/login-signup.component';
import { ManageApplicationsComponent } from './manage-applications/manage-applications.component';
import { PendingApplicationsComponent } from './pending-applications/pending-applications.component';
import { RejectedApplicationsComponent } from './rejected-application/rejected-applications/rejected-applications.component';
import { RejectedBeneficiariesComponent } from './rejected-application/rejected-beneficiaries/rejected-beneficiaries.component';
import { RoleComponent } from './role/role.component';
import { TransactionChannelsComponent } from './transactional-channel/transaction-channels/transaction-channels.component';
import { TransactionTypeComponent } from './transactional-channel/transaction-type/transaction-type.component';
import { TransactionalChannelComponent } from './transactional-channel/transactional-channel.component';

const routes: Routes = [
  {
    path: '',
    component: AdminLayoutComponent,
    children: [
      {
        path: 'auth',
        component: LoginSignupComponent,
        data: { hideNavbar: true },
      },
      {
        path: 'adminHome',
        component: AdminHomeComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'view-banks',
        component: AllBankComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'branch-details/:id',
        component: BranchDetailComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'add-bank',
        component: AddBranchComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'manage-accounts',
        component: AccountFunctionalitiesComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'manage-applications',
        component: ManageApplicationsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'pending-account-applications',
        component: PendingApplicationsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'pending-loan-applications',
        component: PendingApplicationsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'pending-creditCard-applications',
        component: PendingApplicationsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'pending-beneficiary-applications',
        component: BeneficiaryApplicationComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'rejected-applications',
        component: RejectedApplicationsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'rejected-beneficiaries',
        component: RejectedBeneficiariesComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'blocked-accounts',
        component: BlockedAccountsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'block-unblock-accounts',
        component: BlockUnblockAccountsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'transaction-types',
        component: TransactionTypeComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'transaction-channels',
        component: TransactionChannelsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'transacation-channel',
        component: TransactionalChannelComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'transaction-types',
        component: TransactionTypeComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'transaction-channels',
        component: TransactionChannelsComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'manage-debit-card',
        component: CardControllerComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'manage-application-type',
        component: ApplicationTypeComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'manage-document-type',
        component: DocumentTypesComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'manage-loan-type',
        component: LoanTypeComponent,
        canActivate: [routeGuardGuard],
      },
      {
        path: 'manage-roles',
        component: RoleComponent,
        canActivate: [routeGuardGuard],
      },
    ],
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
