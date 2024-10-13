import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { BranchService } from 'src/app/service/admin/branch.service';

@Component({
  selector: 'app-add-branch',
  templateUrl: './add-branch.component.html',
  styleUrls: ['./add-branch.component.css'],
})
export class AddBranchComponent {
  alertMessage: string = '';
  alertClass: string = '';

  constructor(private branchService: BranchService, private router: Router) {}

  onSubmit(branchForm: any): void {
    if (branchForm.valid) {
      const branchData = {
        ifsc: branchForm.value.ifsc,
        addressDto: {
          buildingName: branchForm.value.buildingName,
          street: branchForm.value.street,
          city: branchForm.value.city,
          state: branchForm.value.state,
          country: branchForm.value.country,
          zipcode: branchForm.value.zipcode,
        },
      };

      this.branchService.addBranch(branchData).subscribe({
        next: () => {
          this.showAlert('Branch created successfully!', 'alert-success');
          setTimeout(() => {
            this.router.navigate(['/admin/adminHome']);
          }, 2000);
        },
        error: (error) => {
          this.showAlert('Branch creation failed!', 'alert-danger');
        }
      });
    }
  }

  showAlert(message: string, alertClass: string): void {
    this.alertMessage = message;
    this.alertClass = alertClass;
    setTimeout(() => {
      this.alertMessage = ''; // Clear alert after 3 seconds
    }, 3000);
  }
}
