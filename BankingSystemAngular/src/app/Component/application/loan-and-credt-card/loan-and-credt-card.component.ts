import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { ApplicationService } from 'src/app/service/application.service';
import { FinancialProductApplication } from 'src/app/Model/financialProduct-application';

@Component({
  selector: 'loan-and-credt-card',
  templateUrl: './loan-and-credt-card.component.html',
  styleUrls: ['./loan-and-credt-card.component.css'],
})
export class LoanAndCredtCardComponent implements OnInit {
  financialProductForm!: FormGroup;
  applicationTypes: string[] = [];

  form: FinancialProductApplication = {
    customerId: 0,
    applicationType: '',
  };

  showAlert: boolean = false;
  alert = { type: '', message: '' };

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private application: ApplicationService
  ) {}

  ngOnInit(): void {
    this.financialProductForm = this.fb.group({
      customerId: ['', Validators.required],
      applicationType: ['', Validators.required],
    });

    this.loadApplicationTypes();
  }

  loadApplicationTypes(): void {
    this.application
      .getFinancialProductApplicationType()
      .subscribe((data: string[]) => {
        this.applicationTypes = data;
        console.log(data);
      });
  }

  submitForm(): void {
    this.form = this.financialProductForm.value;
    console.log(this.form);
    this.application.submitFinancialProductApplication(this.form).subscribe({
      next: (response) => {
        // console.log(response);
        this.alert.type = 'success';
        this.alert.message = 'Application Submitted Successfully';
        this.triggerAlert();
        this.financialProductForm.reset();
      },
      error: (error) => {
        console.log(error.error.message);
        this.alert.type = 'danger';
        this.alert.message = error.error.message;
        this.triggerAlert();
        this.financialProductForm.reset();
      },
    });
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }

  goBack(): void {
    this.location.back();
  }
}
