import { Component, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Beneficiary } from 'src/app/Model/beneficiary';
import { AdminService } from 'src/app/service/admin/admin.service';

@Component({
  selector: 'app-beneficiary-application',
  templateUrl: './beneficiary-application.component.html',
  styleUrls: ['./beneficiary-application.component.css'],
})
export class BeneficiaryApplicationComponent {
  beneficiaryApplications: Beneficiary[] = [];
  info?: Beneficiary;
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
    this.loadAllBeneficiaryApplication();
  }

  approveApplication(beneficiaryId: number): void {
    this.adminService.approveBeneficiaries(beneficiaryId).subscribe({
      next: (response) => {
        (this.alert.type = 'success'),
          (this.alert.message = `Beneficiary with ID ${beneficiaryId} Successfully approved.`);
        this.triggerAlert();
        this.loadAllBeneficiaryApplication();
      },
      error: (error) => {
        (this.alert.type = 'danger'),
          (this.alert.message = error.error.message);
        this.triggerAlert();
      },
    });
  }

  loadAllBeneficiaryApplication(): void {
    this.adminService.getBeneficieriesPendingApplication().subscribe({
      next: (response) => {
        this.beneficiaryApplications = response;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  moreInfo(beneficiaryId: number, content: TemplateRef<any>): void {
    this.info = this.beneficiaryApplications.find(
      (beneficiary) => beneficiary.beneficiaryId === beneficiaryId
    );
    this.modalService.open(content, { centered: true });
  }

  rejectBeneficiary(beneficiaryId: number): void {
    this.adminService.rejectBeneficiary(beneficiaryId).subscribe({
      next: (response) => {
        (this.alert.type = 'info'),
          (this.alert.message = `Beneficiary with ID ${beneficiaryId} rejected.`);
        this.triggerAlert();
        this.loadAllBeneficiaryApplication();
      },
      error: (error) => {
        (this.alert.type = 'danger'),
          (this.alert.message = error.error.message);
        this.triggerAlert();
      },
    });
  }

  get filteredApplication(): Beneficiary[] {
    if (!this.searchTerm) {
      return this.beneficiaryApplications;
    }
    return this.beneficiaryApplications.filter(
      (beneficiary) =>
        beneficiary.beneficiaryId === +this.searchTerm.toLowerCase()
    );
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }
}
