import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Beneficiary } from 'src/app/Model/beneficiary';
import { MoneyTransfer } from 'src/app/Model/money-transfer';
import { AccountService } from 'src/app/service/account.service';
import { CustomerService } from 'src/app/service/customer.service';
import { MoneyTransferService } from 'src/app/service/money-transfer.service';
import { TransactionTypeChannelService } from 'src/app/service/admin/transaction-type-channel.service';

@Component({
  selector: 'app-money-transfer',
  templateUrl: './money-transfer.component.html',
  styleUrls: ['./money-transfer.component.css'],
})
export class MoneyTransferComponent {
  showAlert: boolean = false;
  alert = { type: '', message: '' };

  moneyTransfer!: FormGroup;
  accounts: number[] = [];
  beneficiaries: Beneficiary[] = [];
  transactionTypes: string[] = [];

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private transactionService: MoneyTransferService,
    private accountService: AccountService,
    private customerService: CustomerService,
    private transactionTypeService: TransactionTypeChannelService
  ) {}

  ngOnInit(): void {
    this.moneyTransfer = this.fb.group({
      accountNumber: ['', Validators.required],
      beneficiaryAccountNumber: ['', Validators.required],
      transactionType: ['', Validators.required],
      transactionChannel: ['Internet_Banking', Validators.required],
      amount: ['', [Validators.required, Validators.min(100)]],
      remarks: [''],
      tPin: [
        '',
        [
          Validators.required,
          Validators.maxLength(6),
          Validators.pattern(/^\d+$/),
        ],
      ],
    });
    this.loadAccountNumbers();
    this.loadBeneficiaryAccountNumbers();
    this.loadTransactionType();
  }

  onSubmit(): void {
    if (this.moneyTransfer.valid) {
      const transferForm: MoneyTransfer = this.moneyTransfer.value;
      // console.log(transferForm);
      this.transactionService.transferFund(transferForm).subscribe({
        next: (response) => {
          this.alert.type = 'success';
          this.alert.message = response.message;
          this.triggerAlert();
          this.moneyTransfer.reset();
          this.resetForm();
        },
        error: (error) => {
          console.log(error.error.message);
          this.alert.type = 'danger';
          this.alert.message = error.error.message;
          this.triggerAlert();
          this.moneyTransfer.reset();
          this.resetForm();
        },
      });
    }
  }

  loadAccountNumbers(): void {
    this.accountService.getAllAccountNumbers().subscribe({
      next: (response) => {
        this.accounts = response;
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  loadBeneficiaryAccountNumbers(): void {
    this.customerService.getBeneficieries().subscribe({
      next: (response) => {
        this.beneficiaries = response.filter(
          (b) => b.beneficiaryStatus === 'Approved'
        );
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  loadTransactionType(): void {
    this.transactionTypeService.getAllTransactionTypes().subscribe({
      next: (response) => {
        this.transactionTypes = response.map((t) => t.transactionMethod);
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  goBack(): void {
    this.location.back();
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 5000);
  }

  resetForm(): void {
    this.moneyTransfer.patchValue({
      transactionChannel: 'Internet_Banking',
    });
  }
}
