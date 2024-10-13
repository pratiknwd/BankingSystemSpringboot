import { Component } from '@angular/core';
import { Account } from 'src/app/Model/account';
import { AdminService } from 'src/app/service/admin/admin.service';

@Component({
  selector: 'app-block-unblock-accounts',
  templateUrl: './block-unblock-accounts.component.html',
  styleUrls: ['./block-unblock-accounts.component.css'],
})
export class BlockUnblockAccountsComponent {
  accountNumber: string = '';
  account?: Account;
  showAlert: boolean = false;
  alert = { type: '', message: '' };

  constructor(private adminService: AdminService) {}

  searchAccount() {
    this.adminService.getAccountByAccountNumber(+this.accountNumber).subscribe({
      next: (response) => {
        this.account = response;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  blockAccount() {
    this.adminService.blockAccount(+this.accountNumber).subscribe({
      next: (response) => {
        (this.alert.type = 'info'),
          (this.alert.message = `Account number ${this.accountNumber} successfully Blocked.`);
        this.triggerAlert();
        this.searchAccount();
      },
      error: (error) => {
        (this.alert.type = 'danger'),
          (this.alert.message = error.error.message);
        this.triggerAlert();
      },
    });
  }

  unblockAccount() {
    this.adminService.unBlockAccount(+this.accountNumber).subscribe({
      next: (response) => {
        (this.alert.type = 'success'),
          (this.alert.message = `Account number ${this.accountNumber} successfully Unblocked.`);
        this.triggerAlert();
        this.searchAccount();
      },
      error: (error) => {
        (this.alert.type = 'danger'),
          (this.alert.message = error.error.message);
        this.triggerAlert();
      },
    });
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }
}
