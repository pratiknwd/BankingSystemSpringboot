import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgbAlert } from '@ng-bootstrap/ng-bootstrap';
import { NgxPaginationModule } from 'ngx-pagination';
import { DateFromDataandTimePipe } from 'src/app/pipes/date-from-dataand-time.pipe';
import { SharedModule } from 'src/app/shared/shared.module';
import { AccountFunctionalitiesComponent } from './account-functionalities/account-functionalities.component';
import { AddBranchComponent } from './add-branch/add-branch.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminLayoutComponent } from './admin-layout/admin-layout.component';
import { AdminNavbarComponent } from './admin-navbar/admin-navbar.component';
import { AdminRoutingModule } from './admin-routing.module';
import { AllBankComponent } from './all-bank/all-bank.component';
import { BeneficiaryApplicationComponent } from './beneficiary-application/beneficiary-application.component';
import { BranchDetailComponent } from './branch-detail/branch-detail.component';
import { LoginSignupComponent } from './login-signup/login-signup.component';
import { CardControllerComponent } from './card-controller/card-controller.component';
import { ApplicationTypeComponent } from './application-type/application-type.component';
import { DocumentTypesComponent } from './document-types/document-types.component';
import { LoanTypeComponent } from './loan-type/loan-type.component';
import { RoleComponent } from './role/role.component';
import { ManageApplicationsComponent } from './manage-applications/manage-applications.component';
import { PendingApplicationsComponent } from './pending-applications/pending-applications.component';
import { RejectedApplicationsComponent } from './rejected-application/rejected-applications/rejected-applications.component';
import { RejectedBeneficiariesComponent } from './rejected-application/rejected-beneficiaries/rejected-beneficiaries.component';
import { TransactionChannelsComponent } from './transactional-channel/transaction-channels/transaction-channels.component';
import { TransactionTypeComponent } from './transactional-channel/transaction-type/transaction-type.component';
import { TransactionalChannelComponent } from './transactional-channel/transactional-channel.component';
import { BlockUnblockAccountsComponent } from './block-unblock-accounts/block-unblock-accounts.component';
import { BlockedAccountsComponent } from './blocked-accounts/blocked-accounts.component';
;

@NgModule({
  declarations: [
    AdminHomeComponent,
    AllBankComponent,
    BranchDetailComponent,
    AddBranchComponent,
    TransactionalChannelComponent,
    TransactionTypeComponent,
    TransactionChannelsComponent,
    LoginSignupComponent,
    CardControllerComponent,
    ApplicationTypeComponent,
    DocumentTypesComponent,
    LoanTypeComponent,
    RoleComponent,
    AdminLayoutComponent,
    AdminNavbarComponent,
    AccountFunctionalitiesComponent,
    PendingApplicationsComponent,
    DateFromDataandTimePipe,
    ManageApplicationsComponent,
    BeneficiaryApplicationComponent,
    RejectedApplicationsComponent,
    RejectedBeneficiariesComponent,
    BlockUnblockAccountsComponent,
    BlockedAccountsComponent,
  ],
  imports: [
    CommonModule,
    RouterModule,
    AdminRoutingModule,
    FormsModule,
    NgbAlert,
    ReactiveFormsModule,
    NgxPaginationModule,
    NgbAlert,
    SharedModule,
  ],
})
export class AdminModule {}
