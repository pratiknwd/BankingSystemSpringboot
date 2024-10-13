import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AccountComponent } from './Component/application/account/account.component';
import { ApplicationComponent } from './Component/application/application.component';

import { NgxPaginationModule } from 'ngx-pagination';
import { AllToolsComponent } from './Component/FinancialTools/all-tools/all-tools.component';
import { CiCalculatorComponent } from './Component/FinancialTools/ci-calculator/ci-calculator.component';
import { GstCalculatorComponent } from './Component/FinancialTools/gst-calculator/gst-calculator.component';
import { PpfCalculatorComponent } from './Component/FinancialTools/ppf-calculator/ppf-calculator.component';
import { SipCalculatorComponent } from './Component/FinancialTools/sip-calculator/sip-calculator.component';
import { AccountBalanceComponent } from './Component/account-balance/account-balance.component';
import { AdminModule } from './Component/admin/admin.module';
import { AlertComponent } from './Component/alert/alert.component';
import { LoanAndCredtCardComponent } from './Component/application/loan-and-credt-card/loan-and-credt-card.component';
import { ViewApplicationComponent } from './Component/application/view-application/view-application.component';
import { AuthFormComponent } from './Component/auth-form/auth-form.component';
import { AddBeneficiariesComponent } from './Component/beneficiaries/add-beneficiaries/add-beneficiaries.component';
import { ShowBeneficiariesComponent } from './Component/beneficiaries/show-beneficiaries/show-beneficiaries.component';
import { CardsComponent } from './Component/cards/cards.component';
import { DebitCardComponent } from './Component/cards/debit-card/debit-card.component';
import { MasterCCComponent } from './Component/cards/master-cc/master-cc.component';
import { ChangePinComponent } from './Component/change-pin/change-pin.component';
import { CustomerComponent } from './Component/customer/customer.component';
import { EditCustomerComponent } from './Component/edit-customer/edit-customer.component';
import { ForgotPasswordComponent } from './Component/forgot-password/forgot-password.component';
import { HomeComponent } from './Component/home/home.component';
import { InvalidUrlComponent } from './Component/invalid-url/invalid-url.component';
import { MoneyTransferComponent } from './Component/money-transfer/money-transfer.component';
import { NavbarComponent } from './Component/navbar/navbar.component';
import { ProfileComponent } from './Component/profile/profile.component';
import { SettingsComponent } from './Component/settings/settings.component';
import { ShowIfscComponent } from './Component/show-ifsc/show-ifsc.component';
import { StatementsComponent } from './Component/statements/statements.component';
import { UserlayoutComponent } from './Component/userlayout/userlayout.component';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CardNumberFormaterPipe } from './pipes/card-number-formater.pipe';
import { DateFormatorPipe } from './pipes/date-formator.pipe';
import { SharedModule } from './shared/shared.module';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    InvalidUrlComponent,
    HomeComponent,
    AccountComponent,
    ApplicationComponent,
    LoanAndCredtCardComponent,
    CustomerComponent,
    CardsComponent,
    DebitCardComponent,
    MasterCCComponent,
    CardNumberFormaterPipe,
    DateFormatorPipe,
    SettingsComponent,
    MoneyTransferComponent,
    ChangePinComponent,
    StatementsComponent,
    AddBeneficiariesComponent,
    ShowBeneficiariesComponent,
    // BackComponent,
    ViewApplicationComponent,
    AccountBalanceComponent,
    ProfileComponent,
    EditCustomerComponent,
    ShowIfscComponent,
    PpfCalculatorComponent,
    SipCalculatorComponent,
    GstCalculatorComponent,
    CiCalculatorComponent,
    AllToolsComponent,
    AuthFormComponent,
    UserlayoutComponent,
    ForgotPasswordComponent,
    AlertComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    NgbModule,
    FormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    NgxPaginationModule,
    AdminModule,
    SharedModule,
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
