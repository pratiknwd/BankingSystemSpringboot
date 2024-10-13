import { Location } from '@angular/common';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterCustomer } from 'src/app/Model/registerCustomer';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css'],
})
export class CustomerComponent {
  registrationForm!: FormGroup;
  maxDate: string;

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private router: Router
  ) {
    const today = new Date();
    this.maxDate = today.toISOString().split('T')[0];
  }

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      name: ['', Validators.required],
      addressDto: this.fb.group({
        buildingName: ['', Validators.required],
        street: ['', Validators.required],
        city: ['', Validators.required],
        state: ['', Validators.required],
        country: ['', Validators.required],
        zipcode: ['', [Validators.required, Validators.pattern(/^\d{6}$/)]],
      }),
      dateOfBirth: ['', [Validators.required, this.validateDate]],
      phoneNumber: ['', [Validators.required, Validators.pattern(/^\d{10}$/)]],
      gender: ['', [Validators.required]],
    });

    this.checkCustomerDetailPresent();
  }

  validateDate(control: FormControl) {
    const value = control.value;
    const today = new Date();
    const dateOfBirth = new Date(value);

    if (dateOfBirth > today) {
      return { futureDate: true };
    }

    return null;
  }

  onSubmit() {
    if (this.registrationForm.valid) {
      const customer: RegisterCustomer = this.registrationForm.value;
      this.customerService.registerCustomer(customer).subscribe({
        next: (response) => {
          this.router.navigate(['/home']);
        },
        error: (error) => {
          console.log(error.error.message);
        },
      });
    }
  }

  checkCustomerDetailPresent(): void {
    this.customerService.isCustomerPresent().subscribe({
      next: (response) => {
        if (response.message === 'Present') {
          this.router.navigate(['/home']);
        }
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }
}
