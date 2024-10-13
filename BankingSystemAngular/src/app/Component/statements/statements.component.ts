import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { Transactions } from 'src/app/Model/transactions';
import { AccountService } from 'src/app/service/account.service';
import { MoneyTransferService } from 'src/app/service/money-transfer.service';

@Component({
  selector: 'app-statements',
  templateUrl: './statements.component.html',
  styleUrls: ['./statements.component.css'],
})
export class StatementsComponent {
  transactions: Transactions[] = [];
  selectedAccount: string = '';
  accountNumbers: number[] = [];
  showTable: boolean = false;
  currentPage: number = 1;
  itemsPerPage: number = 6;

  constructor(
    private transactionService: MoneyTransferService,
    private location: Location,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.loadAllAccounts();
  }

  search(): void {
    if (this.selectedAccount) {
      this.loadStatement(+this.selectedAccount);
      this.showTable = true;
    } else {
      alert('Please select an account number.');
      this.showTable = false;
    }
  }

  loadStatement(accountNumber: number): void {
    this.transactionService.showTransactions(accountNumber).subscribe({
      next: (response) => {
        this.transactions = response.sort(
          (a, b) => b.transactionId - a.transactionId
        );
        this.currentPage = 1;
        console.log(response);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  loadAllAccounts(): void {
    this.accountService.getAllAccountNumbers().subscribe({
      next: (response) => {
        this.accountNumbers = response;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  get paginatedTransactions(): Transactions[] {
    const start = (this.currentPage - 1) * this.itemsPerPage;
    const end = start + this.itemsPerPage;
    return this.transactions.slice(start, end);
  }

  get totalPages(): number {
    return Math.ceil(this.transactions.length / this.itemsPerPage);
  }

  goToPage(page: number): void {
    if (page > 0 && page <= this.totalPages) {
      this.currentPage = page;
    }
  }
  goBack(): void {
    this.location.back();
  }
}
