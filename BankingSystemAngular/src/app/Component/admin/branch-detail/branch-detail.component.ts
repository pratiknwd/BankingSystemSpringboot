import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { BranchService } from 'src/app/service/admin/branch.service';

@Component({
  selector: 'app-branch-detail',
  templateUrl: './branch-detail.component.html',
  styleUrls: ['./branch-detail.component.css']
})
export class BranchDetailComponent implements OnInit {
  branch: any;

  constructor(
    private route: ActivatedRoute,
    private branchService: BranchService
  ) {}

  ngOnInit(): void {
    const branchId = this.route.snapshot.paramMap.get('id');
    
    if (branchId) {
      this.branchService.getBranchById(branchId).subscribe(data => {
        this.branch = data;
      });
    }
  }
}
