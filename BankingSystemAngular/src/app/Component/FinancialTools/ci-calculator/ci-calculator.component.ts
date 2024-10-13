import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-ci-calculator',
  templateUrl: './ci-calculator.component.html',
  styleUrls: ['./ci-calculator.component.css'],
})
export class CiCalculatorComponent {
  compoundForm: FormGroup;
  results: any[] = [];
  amountWarning: string | null = null;
  yearsWarning: string | null = null;

  constructor(private fb: FormBuilder) {
    this.compoundForm = this.fb.group({
      principal: [
        null,
        [Validators.required, Validators.min(500), Validators.max(150000)],
      ],
      investmentAmountRepeat: [
        null,
        [Validators.required, Validators.min(500), Validators.max(150000)],
      ],
      years: [
        null,
        [Validators.required, Validators.min(15), Validators.max(50)],
      ],
      frequency: ['monthly'],
      annualInterestRate: [null, [Validators.required, Validators.min(0)]],
    });
  }

  calculateCompoundInterest() {
    if (this.compoundForm.invalid) {
      this.amountWarning = this.compoundForm.get('principal')?.errors
        ? 'Amount must be between 500 and 150000.'
        : null;
      this.yearsWarning = this.compoundForm.get('years')?.errors
        ? 'Years must be between 15 and 50.'
        : null;
      return;
    }

    this.amountWarning = null;
    this.yearsWarning = null;

    const principal = this.compoundForm.get('principal')?.value ?? 0;
    const investmentAmountRepeat =
      this.compoundForm.get('investmentAmountRepeat')?.value ?? 0;
    const years = this.compoundForm.get('years')?.value ?? 0;
    const frequency = this.compoundForm.get('frequency')?.value ?? 'monthly';
    const annualInterestRate =
      (this.compoundForm.get('annualInterestRate')?.value ?? 0) / 100;

    this.results = [];

    let totalAmountForYear = principal;

    for (let year = 1; year <= years; year++) {
      const interest = totalAmountForYear * annualInterestRate;
      totalAmountForYear += interest;

      this.results.push({
        year,
        principalAmount: totalAmountForYear.toFixed(2),
        interestEarned: interest.toFixed(2),
        amountAfterYear: totalAmountForYear.toFixed(2),
      });

      if (frequency === 'monthly') {
        totalAmountForYear += investmentAmountRepeat * 12;
      } else if (frequency === 'quarterly') {
        totalAmountForYear += investmentAmountRepeat * 4;
      } else if (frequency === 'annually') {
        totalAmountForYear += investmentAmountRepeat;
      }
    }
  }
}
