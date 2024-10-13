import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from 'src/app/Model/account';
import { Customer } from 'src/app/Model/customer';
import { AccountService } from 'src/app/service/account.service';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent {
  customer: Customer = {
    customerId: 0,
    user: {
      username: '',
      email: '',
    },
    name: '',
    gender: '',
    address: {
      buildingName: '',
      street: '',
      city: '',
      state: '',
      country: '',
      zipcode: 0,
    },
    dateOfBirth: '',
    phoneNumber: '',
  };

  accounts: Account[] = [];

  constructor(
    private customerService: CustomerService,
    private accountService: AccountService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAllAccounts();
    this.loadCustomerInfo();
  }

  loadAllAccounts(): void {
    this.accountService.getAllAccount().subscribe({
      next: (response) => {
        this.accounts = this.removeTpinIfAccountBlocked(response);
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  removeTpinIfAccountBlocked(accounts: Account[]): Account[] {
    return accounts.map((account) => {
      if (account.accountStatus === 'Blocked') {
        return { ...account, tpin: null };
      }
      return account;
    });
  }

  loadCustomerInfo(): void {
    this.customerService.getCurrentCustomerInfo().subscribe({
      next: (response) => {
        this.customer = response;
        console.log(response);
        console.log(this.customer.user.username);
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  editPage(): void {
    this.router.navigate(['/updateDetails']);
  }

  navigateToAccountApplication(): void {
    this.router.navigate(['/application/account']);
  }
}
