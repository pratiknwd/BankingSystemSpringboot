import { Component, TemplateRef } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PendingApplicaiton } from 'src/app/Model/admin/PendingApplication';
import { AdminService } from 'src/app/service/admin/admin.service';

@Component({
  selector: 'app-rejected-applications',
  templateUrl: './rejected-applications.component.html',
  styleUrls: ['./rejected-applications.component.css'],
})
export class RejectedApplicationsComponent {
  rejectedApplication: PendingApplicaiton[] = [];
  info?: PendingApplicaiton;
  currentPage: number = 1;
  itemsPerPage: number = 6;
  searchTerm: string = '';
  showAlert: boolean = false;
  alert = { type: '', message: '' };

  constructor(
    private adminService: AdminService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.loadAllRejectedapplications();
  }

  loadAllRejectedapplications() {
    this.adminService.getRejectedApplications().subscribe({
      next: (response) => {
        this.rejectedApplication = response;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  approveApplication(applicationNumber: number, applicaionType: string): void {
    if (applicaionType.toLowerCase().includes('account')) {
      this.adminService.approveAccountApplication(applicationNumber).subscribe({
        next: (response) => {
          (this.alert.type = 'success'),
            (this.alert.message = `Application number ${applicationNumber} Successfully approved.`);
          this.triggerAlert();
          this.loadAllRejectedapplications();
        },
        error: (error) => {
          (this.alert.type = 'danger'),
            (this.alert.message = error.error.message);
          this.triggerAlert();
        },
      });
    } else if (applicaionType.toLowerCase().includes('loan')) {
      this.adminService.approveLoanApplication(applicationNumber).subscribe({
        next: (response) => {
          (this.alert.type = 'success'),
            (this.alert.message = `Application number ${applicationNumber} Successfully approved.`);
          this.triggerAlert();
          this.loadAllRejectedapplications();
        },
        error: (error) => {
          (this.alert.type = 'danger'),
            (this.alert.message = error.error.message);
          this.triggerAlert();
        },
      });
    } else if (applicaionType.toLowerCase().includes('creditcard')) {
      this.adminService
        .approveCreditCardApplication(applicationNumber)
        .subscribe({
          next: (response) => {
            (this.alert.type = 'success'),
              (this.alert.message = `Application number ${applicationNumber} Successfully approved.`);
            this.triggerAlert();
            this.loadAllRejectedapplications();
          },
          error: (error) => {
            (this.alert.type = 'danger'),
              (this.alert.message = error.error.message);
            this.triggerAlert();
          },
        });
    }
  }

  moreInfo(applicationId: number, content: TemplateRef<any>): void {
    this.info = this.rejectedApplication.find(
      (application) => application.applicationId === applicationId
    );
    this.modalService.open(content, { centered: true });
  }

  get filteredApplication(): PendingApplicaiton[] {
    if (!this.searchTerm) {
      return this.rejectedApplication;
    }
    return this.rejectedApplication.filter(
      (applicaiton) =>
        applicaiton.applicationNumber === +this.searchTerm.toLowerCase()
    );
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }
}
