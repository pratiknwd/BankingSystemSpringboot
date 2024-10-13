import { Component, OnInit, TemplateRef } from '@angular/core';
import { BranchService } from 'src/app/service/admin/branch.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Branch } from 'src/app/Model/branch';
import { EditBranch } from 'src/app/Model/admin/editBranch';

@Component({
  selector: 'app-all-banks',
  templateUrl: './all-bank.component.html',
  styleUrls: ['./all-bank.component.css'],
})
export class AllBankComponent implements OnInit {
  branches: any[] = [];
  selectedBranch: any = null;
  branchToEdit: any;

  constructor(
    private branchService: BranchService,
    private modalService: NgbModal
  ) {}

  ngOnInit(): void {
    this.getAllBranches();
  }

  getAllBranches(): void {
    this.branchService.getAllBranches().subscribe((data) => {
      this.branches = data;
    });
  }

  openDeleteModal(branch: any, content: TemplateRef<any>): void {
    this.selectedBranch = branch;
    this.modalService.open(content, { centered: true });
  }

  deleteBranch(): void {
    if (this.selectedBranch) {
      console.log(this.selectedBranch.branchId);
      this.branchService.deleteBranch(this.selectedBranch.branchId).subscribe({
        next: () => {
          this.branches = this.branches.filter(
            (b) => b.branchId !== this.selectedBranch.branchId
          );
          this.modalService.dismissAll();
          alert('Branch deleted successfully!');
        },
        error: (error) => {
          console.error('Failed to delete branch:', error);
          alert('Error deleting branch!');
        },
      });
    }
  }
  openEditModal(branch: any, editModal: TemplateRef<any>): void {
    this.branchToEdit = {
      ...branch,
      address: {
        buildingName: branch?.address?.buildingName || '',
        street: branch?.address?.street || '',
        city: branch?.address?.city || '',
        state: branch?.address?.state || '',
        country: branch?.address?.country || '',
        zipcode: branch?.address?.zipcode || '',
      },
    };
    this.modalService.open(editModal);
  }

  updateBranch(): void {
    if (this.branchToEdit) {
      const editBranch: EditBranch = {
        ifsc: this.branchToEdit.ifsc,
        addressDto: {
          buildingName: this.branchToEdit.address.buildingName,
          street: this.branchToEdit.address.street,
          city: this.branchToEdit.address.city,
          state: this.branchToEdit.address.state,
          country: this.branchToEdit.address.country,
          zipcode: this.branchToEdit.address.zipcode,
        },
      };
      this.branchService
        .updateBranch(+this.branchToEdit.branchId, editBranch)
        .subscribe({
          next: () => {
            this.getAllBranches();
            this.modalService.dismissAll();
            alert('Branch updated successfully!');
          },
          error: (error) => {
            console.error('Failed to update branch:', error);
            alert('Error updating branch!');
          },
        });
    }
  }
}
