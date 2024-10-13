import { Component } from '@angular/core';
import { Branch } from 'src/app/Model/branch';
import { BranchService } from 'src/app/service/branch.service';

@Component({
  selector: 'app-show-ifsc',
  templateUrl: './show-ifsc.component.html',
  styleUrls: ['./show-ifsc.component.css'],
})
export class ShowIfscComponent {
  branches: Branch[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 6;
  searchTerm: string = '';

  constructor(private branchService: BranchService) {}

  ngOnInit(): void {
    this.loadAllBranches();
  }

  loadAllBranches(): void {
    this.branchService.getAllBranch().subscribe({
      next: (response) => {
        this.branches = response;
      },
      error: (error) => {
        console.log(error);
      },
    });
  }

  get filteredBranches(): Branch[] {
    if (!this.searchTerm) {
      return this.branches;
    }
    return this.branches.filter((branch) =>
      branch.address.city.toLowerCase().includes(this.searchTerm.toLowerCase())
    );
  }
}
