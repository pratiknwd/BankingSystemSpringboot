import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AddBeneficiary } from 'src/app/Model/add-beneficiary';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-add-beneficiaries',
  templateUrl: './add-beneficiaries.component.html',
  styleUrls: ['./add-beneficiaries.component.css'],
})
export class AddBeneficiariesComponent implements OnInit {
  beneficiaryForm!: FormGroup;
  showAlert: boolean = false;
  alert = { type: '', message: '' };

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private customerService: CustomerService
  ) {}

  ngOnInit(): void {
    this.beneficiaryForm = this.fb.group({
      beneficiaryAccountNumber: [
        '',
        [Validators.required, Validators.pattern('^[0-9]*$')],
      ],
      beneficiaryName: ['', Validators.required],
      beneficiaryBankName: ['', Validators.required],
      beneficiaryIFSC: [
        '',
        [Validators.required, Validators.pattern('^[A-Z][A-Z0-9]*$')],
      ],
    });
  }

  onSubmit(): void {
    if (this.beneficiaryForm.valid) {
      const beneficiary: AddBeneficiary = this.beneficiaryForm.value;
      console.log(beneficiary);
      this.customerService.addBeneficiary(beneficiary).subscribe({
        next: (response) => {
          console.log(response);
          this.alert.type = 'success';
          this.alert.message = response.message;
          this.triggerAlert();
          this.beneficiaryForm.reset();
        },
        error: (error) => {
          this.alert.type = 'danger';
          this.alert.message = error.error.message;
          this.triggerAlert();
        },
      });
    } else {
      // Mark all fields as touched to trigger validation messages
      this.beneficiaryForm.markAllAsTouched();
    }
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
