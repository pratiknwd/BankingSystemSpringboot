import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Account } from 'src/app/Model/account';
import { AccountService } from 'src/app/service/account.service';

@Component({
  selector: 'app-account-balance',
  templateUrl: './account-balance.component.html',
  styleUrls: ['./account-balance.component.css'],
})
export class AccountBalanceComponent {
  accountNumbers: number[] = [];
  selectedAccount: string = '';
  balance: number = 0;

  constructor(private accountService: AccountService, private router: Router) {}

  ngOnInit(): void {
    this.fetchAccountsNumbers();
  }

  fetchAccountsNumbers(): void {
    this.accountService.getAllAccountNumbers().subscribe({
      next: (response) => {
        this.accountNumbers = response;
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  fetchBalance(): void {
    this.accountService.getAllAccount().subscribe({
      next: (response) => {
        const account: Account | undefined = response.find(
          (b) => b.accountNumber === +this.selectedAccount
        );
        if (account) {
          this.balance = account.balance;
        }
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  navigateToTransaction(): void {
    this.router.navigate(['accountStatement']);
  }
}
