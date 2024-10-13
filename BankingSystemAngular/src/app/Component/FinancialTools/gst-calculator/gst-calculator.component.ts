import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-gst-calculator',
  templateUrl: './gst-calculator.component.html',
  styleUrls: ['./gst-calculator.component.css'],
})
export class GstCalculatorComponent {
  gstForm: FormGroup;
  showResults = false;
  actualAmount: number = 0;
  gstAmount: number = 0;
  totalAmount: number = 0;

  constructor(private fb: FormBuilder) {
    this.gstForm = this.fb.group({
      amount: ['', [Validators.required, Validators.min(1)]],
      gstRate: ['', Validators.required],
      inclusiveness: ['', Validators.required],
    });
  }

  calculateGST() {
    console.log('Calculate button clicked'); // Debug: Button click check

    if (this.gstForm.invalid) {
      console.log('Form is invalid'); // Debug: Form validation check
      this.gstForm.markAllAsTouched();
      return;
    }

    const { amount, gstRate, inclusiveness } = this.gstForm.value;
    console.log('Form Values:', this.gstForm.value); // Debug: Log form values

    const parsedAmount = Number(amount);

    if (isNaN(parsedAmount) || parsedAmount <= 0) {
      console.error('Invalid amount'); // Debug: Check for invalid amount
      return;
    }

    let gstAmount = 0;
    let totalAmount = 0;

    if (inclusiveness === 'exclusive') {
      gstAmount = (parsedAmount * gstRate) / 100;
      totalAmount = parsedAmount + gstAmount;
      this.actualAmount = parsedAmount;
    } else if (inclusiveness === 'inclusive') {
      gstAmount = (parsedAmount * gstRate) / (100 + gstRate);
      totalAmount = parsedAmount;
      this.actualAmount = parsedAmount - gstAmount;
    }

    this.gstAmount = gstAmount;
    this.totalAmount = totalAmount;

    console.log('GST Amount:', gstAmount); // Debug: Check GST amount calculation
    console.log('Total Amount:', totalAmount); // Debug: Check total amount calculation

    // Show the results after calculation
    this.showResults = true;
    console.log('Show Results:', this.showResults); // Debug: Check if results are displayed
  }
}
