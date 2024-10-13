import { Component } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { LoanTypeService } from 'src/app/service/admin/loan-type.service';
import { LoanType } from 'src/app/Model/admin/loan-type.model';

@Component({
  selector: 'app-loan-type',
  templateUrl: './loan-type.component.html',
  styleUrls: ['./loan-type.component.css']
})
export class LoanTypeComponent {
  loanTypes: LoanType[] = [];
  selectedLoanType: LoanType | null = null;
  newLoanType: string = '';
  newLoanInterest: number = 0;
  isEditMode: boolean = false;

  constructor(private loanTypeService: LoanTypeService, private modalService: NgbModal) {}

  ngOnInit(): void {
    this.getAllLoanTypes();
  }

  getAllLoanTypes() {
    this.loanTypeService.getAllLoanTypes().subscribe(
      (data) => this.loanTypes = data,
      (error) => console.error('Failed to load loan types:', error)
    );
  }

  openModal(content: any, loanType?: LoanType) {
    if (loanType) {
      this.isEditMode = true;
      this.selectedLoanType = loanType;
      this.newLoanType = loanType.loanType;
      this.newLoanInterest = loanType.loanInterest;
    } else {
      this.isEditMode = false;
      this.newLoanType = '';
      this.newLoanInterest = 0;
    }
    this.modalService.open(content, { centered: true });
  }

  addLoanType() {
    const newLoan: Partial<LoanType> = {
      loanType: this.newLoanType,
      loanInterest: this.newLoanInterest
    };

    this.loanTypeService.addLoanType(newLoan).subscribe(
      () => {
        alert('Loan Type added successfully!');
        this.getAllLoanTypes();
        this.modalService.dismissAll();
      },
      (error) => {
        console.error('Failed to add loan type:', error);
        alert( (error.error?.message ));
      }
    );
  }

  updateLoanType() {
    if (this.selectedLoanType) {
      this.loanTypeService.updateLoanType(this.selectedLoanType.loanTypeId, {
        loanType: this.newLoanType,
        loanInterest: this.newLoanInterest
      }).subscribe(
        () => {
          alert('Loan Type updated successfully!');
          this.getAllLoanTypes();
          this.modalService.dismissAll();
        },
        (error) => {
          console.error('Failed to update loan type:', error);
          alert( (error.error?.message ));
        }
      );
    }
  }

  deleteLoanType(loanTypeId: number) {
    this.loanTypeService.deleteLoanType(loanTypeId).subscribe(
      () => {
        alert('Loan Type deleted successfully!');
        this.getAllLoanTypes();
      },
      (error) => console.error('Failed to delete loan type:', error)
    );
  }
}
