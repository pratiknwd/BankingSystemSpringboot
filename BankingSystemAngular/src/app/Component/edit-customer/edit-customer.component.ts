import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css'],
})
export class EditCustomerComponent {
  profileForm!: FormGroup;
  heading = 'Update Profile';
  showAlert = false;
  alert = { type: 'success', message: '' };

  constructor(
    private fb: FormBuilder,
    private customerService: CustomerService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      address: this.fb.group({
        buildingName: ['', Validators.required],
        street: ['', Validators.required],
        city: ['', Validators.required],
        state: ['', Validators.required],
        country: ['', Validators.required],
        zipcode: ['', [Validators.required, Validators.pattern(/^\d+$/)]],
      }),
      gender: ['', Validators.required],
      phoneNumber: ['', Validators.required],
    });

    this.loadProfile();
  }

  loadProfile(): void {
    this.customerService.getCurrentCustomerInfo().subscribe({
      next: (response) => {
        this.profileForm.patchValue({
          name: response.name,
          email: response.user.email,
          address: {
            buildingName: response.address.buildingName,
            street: response.address.street,
            city: response.address.city,
            state: response.address.state,
            country: response.address.country,
            zipcode: response.address.zipcode,
          },
          gender: response.gender,
          phoneNumber: response.phoneNumber,
        });
      },
      error: (error) => console.error('Error loading profile:', error),
    });
  }

  onSubmit(): void {
    if (this.profileForm.valid) {
      this.customerService.updateCustomer(this.profileForm.value).subscribe({
        next: (response) => {
          this.alert = {
            type: 'success',
            message: response.message,
          };
          this.triggerAlert();
          this.loadProfile();
          // Delay the navigation by 2 seconds
          setTimeout(() => {
            this.router.navigate(['/profile']);
          }, 2000);
        },
        error: (error) => {
          this.alert = {
            type: 'danger',
            message: 'Something went wrong...!',
          };
          this.triggerAlert();
        },
      });
    }
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }
}
