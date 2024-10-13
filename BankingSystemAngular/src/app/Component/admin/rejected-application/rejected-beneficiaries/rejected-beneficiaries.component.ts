import { Component, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Beneficiary } from 'src/app/Model/beneficiary';
import { AdminService } from 'src/app/service/admin/admin.service';

@Component({
  selector: 'app-rejected-beneficiaries',
  templateUrl: './rejected-beneficiaries.component.html',
  styleUrls: ['./rejected-beneficiaries.component.css'],
})
export class RejectedBeneficiariesComponent {
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
    this.loadAllRejectedBeneficiary();
  }

  approveApplication(beneficiaryId: number): void {
    this.adminService.approveBeneficiaries(beneficiaryId).subscribe({
      next: (response) => {
        (this.alert.type = 'success'),
          (this.alert.message = `Beneficiary with ID ${beneficiaryId} Successfully approved.`);
        this.triggerAlert();
        this.loadAllRejectedBeneficiary();
      },
      error: (error) => {
        (this.alert.type = 'danger'),
          (this.alert.message = error.error.message);
        this.triggerAlert();
      },
    });
  }

  loadAllRejectedBeneficiary(): void {
    this.adminService.getBeneficiariesRejectedApplications().subscribe({
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
