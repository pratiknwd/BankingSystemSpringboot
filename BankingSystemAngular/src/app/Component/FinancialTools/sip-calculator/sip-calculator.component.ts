import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-sip-calculator',
  templateUrl: './sip-calculator.component.html',
  styleUrls: ['./sip-calculator.component.css'],
})
export class SipCalculatorComponent {
  sipForm: FormGroup;
  resultVisible = false;
  formattedInvestment: string = '';
  formattedTotalInvestment: string = '';
  formattedSIPValue: string = '';
  ageAtEnd: number = 0;
  growthPercentage: string = '';

  constructor(private fb: FormBuilder) {
    this.sipForm = this.fb.group({
      age: [
        null,
        [Validators.required, Validators.min(18), Validators.max(65)],
      ],
      investment: [null, Validators.required],
      frequency: ['12'],
      years: [null, Validators.required],
      return: [null, Validators.required],
    });
  }

  calculateSIP() {
    if (this.sipForm.invalid) {
      return;
    }

    const age = this.sipForm.get('age')?.value || 0;
    const investment = this.sipForm.get('investment')?.value || 0;
    const frequency = this.sipForm.get('frequency')?.value || 12;
    const years = this.sipForm.get('years')?.value || 0;
    const rateOfReturn = (this.sipForm.get('return')?.value || 0) / 100;

    const totalPayments = years * frequency;
    const periodicRate = rateOfReturn / frequency;

    const sipValue =
      ((investment * (Math.pow(1 + periodicRate, totalPayments) - 1)) /
        periodicRate) *
      (1 + periodicRate);
    const totalInvestment = investment * totalPayments;

    this.formattedInvestment = this.formatNumber(investment);
    this.formattedTotalInvestment = this.formatNumber(totalInvestment);
    this.formattedSIPValue = this.formatNumber(+sipValue.toFixed(2));
    this.ageAtEnd = age + years;
    this.growthPercentage = this.calculateGrowth(totalInvestment, sipValue);

    this.resultVisible = true;
  }

  formatNumber(num: number): string {
    if (num >= 10000000) {
      return (num / 10000000).toFixed(1) + ' Cr';
    } else if (num >= 100000) {
      return (num / 100000).toFixed(1) + ' Lakh';
    }
    return num.toLocaleString();
  }

  calculateGrowth(initial: number, final: number): string {
    return (((final - initial) / initial) * 100).toFixed(1);
  }
}
