import { Component, TemplateRef } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PendingApplicaiton } from 'src/app/Model/admin/PendingApplication';
import { AdminService } from 'src/app/service/admin/admin.service';

@Component({
  selector: 'app-pending-applications',
  templateUrl: './pending-applications.component.html',
  styleUrls: ['./pending-applications.component.css'],
})
export class PendingApplicationsComponent {
  applications: PendingApplicaiton[] = [];
  info?: PendingApplicaiton;
  currentPage: number = 1;
  itemsPerPage: number = 6;
  searchTerm: string = '';

  pageTitle: string = '';

  showAlert: boolean = false;
  alert = { type: '', message: '' };

  constructor(
    private adminService: AdminService,
    private modalService: NgbModal,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.updateDom();

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.updateDom();
      }
    });
  }

  updateDom(): void {
    const url = this.router.url;
    if (url.includes('pending-account-applications')) {
      this.pageTitle = 'Account Application';
      this.loadAllAccountApplications();
    } else if (url.includes('pending-loan-application')) {
      this.pageTitle = 'Loan Application';
      this.loadAllLoansApplication();
    } else if (url.includes('pending-creditCard-application')) {
      this.pageTitle = 'Credit Card Application';
      this.loadAllCreditCardApplication();
    }
  }
  loadAllAccountApplications(): void {
    this.adminService.getAccountPendingApplications().subscribe({
      next: (response) => {
        this.applications = response;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  loadAllCreditCardApplication(): void {
    this.adminService.getCreditCardPendingApplications().subscribe({
      next: (response) => {
        this.applications = response;
        console.log(this.applications);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  loadAllLoansApplication(): void {
    this.adminService.getLoanPendingApplications().subscribe({
      next: (response) => {
        this.applications = response;
        console.log(this.applications);
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  moreInfo(applicationId: number, content: TemplateRef<any>): void {
    this.info = this.applications.find(
      (application) => application.applicationId === applicationId
    );
    this.modalService.open(content, { centered: true });
  }

  approveApplication(applicationNumber: number): void {
    const url = this.router.url;
    if (url.includes('pending-account-applications')) {
      this.adminService.approveAccountApplication(applicationNumber).subscribe({
        next: (response) => {
          (this.alert.type = 'success'),
            (this.alert.message = `Application number ${applicationNumber} Successfully approved.`);
          this.triggerAlert();
          this.loadAllAccountApplications();
        },
        error: (error) => {
          (this.alert.type = 'danger'),
            (this.alert.message = error.error.message);
          this.triggerAlert();
        },
      });
    } else if (url.includes('pending-loan-application')) {
      this.adminService.approveLoanApplication(applicationNumber).subscribe({
        next: (response) => {
          (this.alert.type = 'success'),
            (this.alert.message = `Application number ${applicationNumber} Successfully approved.`);
          this.triggerAlert();
          this.loadAllLoansApplication();
        },
        error: (error) => {
          (this.alert.type = 'danger'),
            (this.alert.message = error.error.message);
          this.triggerAlert();
        },
      });
    } else if (url.includes('pending-creditCard-application')) {
      this.adminService
        .approveCreditCardApplication(applicationNumber)
        .subscribe({
          next: (response) => {
            (this.alert.type = 'success'),
              (this.alert.message = `Application number ${applicationNumber} Successfully approved.`);
            this.triggerAlert();
            this.loadAllCreditCardApplication();
          },
          error: (error) => {
            (this.alert.type = 'danger'),
              (this.alert.message = error.error.message);
            this.triggerAlert();
          },
        });
    }
  }

  rejectApplication(applicationNumber: number): void {
    this.adminService.rejectApplication(applicationNumber).subscribe({
      next: (response) => {
        (this.alert.type = 'info'),
          (this.alert.message = `Application number ${applicationNumber} rejected.`);
        this.triggerAlert();
        this.updateDom();
      },
      error: (error) => {
        (this.alert.type = 'danger'),
          (this.alert.message = error.error.message);
        this.triggerAlert();
      },
    });
  }

  get filteredApplication(): PendingApplicaiton[] {
    if (!this.searchTerm) {
      return this.applications;
    }
    return this.applications.filter(
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
