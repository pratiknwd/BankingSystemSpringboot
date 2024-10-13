import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Location } from '@angular/common';
import { ApplicationService } from 'src/app/service/application.service';
import { AccountApplication } from 'src/app/Model/account-application';
import { BranchService } from 'src/app/service/branch.service';
import { Router } from '@angular/router';

@Component({
  selector: 'account-application',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css'],
})
export class AccountComponent implements OnInit {
  registrationForm!: FormGroup;
  applicationTypes: string[] = [];
  ifsc: string[] = [];
  accountApplication: AccountApplication = {
    customerId: 0,
    applicationType: '',
    ifsc: '',
  };

  showAlert: boolean = false;
  alert = { type: '', message: '' };

  constructor(
    private fb: FormBuilder,
    private location: Location,
    private application: ApplicationService,
    private branchService: BranchService,
    private router:Router
  ) {}

  ngOnInit(): void {
    this.registrationForm = this.fb.group({
      customerId: ['', [Validators.required]],
      ifsc: ['', Validators.required],
      applicationType: ['', Validators.required],
    });

    this.loadApplicationTypes();
    this.loadIfscCodes();
  }

  loadApplicationTypes(): void {
    this.application.getAccountApplicationType().subscribe((data: string[]) => {
      this.applicationTypes = data;
      // console.log(data);
    });
  }

  loadIfscCodes(): void {
    this.branchService.getAllIfsc().subscribe((data: string[]) => {
      this.ifsc = data;
      // console.log(data);
    });
  }

  onSubmit(): void {
    if (this.registrationForm.valid) {
      console.log(this.registrationForm.value);
      this.accountApplication = this.registrationForm.value;
      this.application
        .submitAccountApplication(this.accountApplication)
        .subscribe({
          next: (response) => {
            // console.log(response);
            this.alert.type = 'success';
            this.alert.message = 'Application Submitted Successfully';
            this.triggerAlert();
            this.registrationForm.reset();
          },
          error: (error) => {
            console.log(error.error.message);
            this.alert.type = 'danger';
            this.alert.message = error.error.message;
            this.triggerAlert();
            this.registrationForm.reset();
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
  goToBranch(){
    this.router.navigate(['/branches'])
  }

  goBack(): void {
    this.location.back();
  }
}
