import { Component } from '@angular/core';

@Component({
  selector: 'app-ppf-calculator',
  templateUrl: './ppf-calculator.component.html',
  styleUrls: ['./ppf-calculator.component.css'],
})
export class PpfCalculatorComponent {
  investmentAmount!: number;
  investmentFrequency: string = 'monthly';
  totalYears!: number;
  rateOfInterest: number = 0.071; // Fixed interest rate of 7.1%

  totalInvestedAmount: number = 0;
  totalInterest: number = 0;
  maturityAmount: number = 0;

  amountWarning: string = '';
  yearsWarning: string = '';
  showResults: boolean = false;

  calculatePPF(): void {
    this.clearWarnings();
    let isValid = this.validateInputs();

    if (!isValid) {
      this.showResults = false;
      return;
    }

    let multiplier = this.getFrequencyMultiplier();

    const P = this.investmentAmount * multiplier;
    const r = this.rateOfInterest;
    const n = this.totalYears;

    const onePlusR = 1 + r;
    const futureValue = P * (((Math.pow(onePlusR, n) - 1) / r) * onePlusR);

    this.totalInvestedAmount =
      this.investmentAmount * multiplier * this.totalYears;
    this.totalInterest = futureValue - this.totalInvestedAmount;
    this.maturityAmount = futureValue;

    this.showResults = true;
  }

  clearWarnings(): void {
    this.amountWarning = '';
    this.yearsWarning = '';
  }

  validateInputs(): boolean {
    let isValid = true;
    if (
      isNaN(this.investmentAmount) ||
      this.investmentAmount < 500 ||
      this.investmentAmount > 150000
    ) {
      this.amountWarning = 'Amount must be between 500 and 150,000.';
      isValid = false;
    }

    if (
      isNaN(this.totalYears) ||
      this.totalYears < 15 ||
      this.totalYears > 50
    ) {
      this.yearsWarning = 'Total Years must be between 15 and 50.';
      isValid = false;
    }

    return isValid;
  }

  getFrequencyMultiplier(): number {
    switch (this.investmentFrequency) {
      case 'monthly':
        return 12;
      case 'quarterly':
        return 4;
      case 'annually':
        return 1;
      default:
        return 1;
    }
  }
}
