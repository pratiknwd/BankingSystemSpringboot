import { Component, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Account } from 'src/app/Model/account';
import { PendingApplicaiton } from 'src/app/Model/admin/PendingApplication';
import { AdminService } from 'src/app/service/admin/admin.service';

@Component({
  selector: 'app-blocked-accounts',
  templateUrl: './blocked-accounts.component.html',
  styleUrls: ['./blocked-accounts.component.css'],
})
export class BlockedAccountsComponent {
  blockedAccounts: Account[] = [];
  info?: Account;
  currentPage: number = 1;
  itemsPerPage: number = 6;
  searchTerm: string = '';
  showAlert: boolean = false;
  alert = { type: '', message: '' };

  constructor(
    private adminService: AdminService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.loadAllBlockedAccounts();
  }

  loadAllBlockedAccounts() {
    this.adminService.getBlockedAccounts().subscribe({
      next: (response) => {
        this.blockedAccounts = response;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  ublockAccount(accountNumber: number): void {
    this.adminService.unBlockAccount(accountNumber).subscribe({
      next: (response) => {
        (this.alert.type = 'success'),
          (this.alert.message = `Application ${accountNumber} Successfully unlocked.`);
        this.triggerAlert();
        this.loadAllBlockedAccounts();
      },
      error: (error) => {
        (this.alert.type = 'danger'),
          (this.alert.message = error.error.message);
        this.triggerAlert();
      },
    });
  }

  moreInfo(accountId: number, content: TemplateRef<any>): void {
    this.info = this.blockedAccounts.find(
      (account) => account.accountId === accountId
    );
    this.modalService.open(content, { centered: true });
  }

  get filteredAccounts(): Account[] {
    if (!this.searchTerm) {
      return this.blockedAccounts;
    }
    return this.blockedAccounts.filter(
      (account) => account.accountNumber === +this.searchTerm.toLowerCase()
    );
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }
}
