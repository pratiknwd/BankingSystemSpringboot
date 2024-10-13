import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Beneficiary } from 'src/app/Model/beneficiary';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-show-beneficiaries',
  templateUrl: './show-beneficiaries.component.html',
  styleUrls: ['./show-beneficiaries.component.css'],
})
export class ShowBeneficiariesComponent {
  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {}

  beneficiaries: Beneficiary[] = [];

  ngOnInit(): void {
    this.loadBeneficiaries();
  }

  loadBeneficiaries(): void {
    this.customerService.getBeneficieries().subscribe({
      next: (response) => {
        this.beneficiaries = response;
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  navigateToAddBeneficiary(): void {
    this.router.navigate(['/beneficiaries/add']);
  }
}
