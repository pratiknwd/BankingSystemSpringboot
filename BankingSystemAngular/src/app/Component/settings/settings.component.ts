import { Component, inject, TemplateRef, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

import {
  NgbOffcanvas,
  OffcanvasDismissReasons,
} from '@ng-bootstrap/ng-bootstrap';
import { AuthService } from 'src/app/service/auth.service';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.component.html',
  styleUrls: ['./settings.component.css'],
})
export class SettingsComponent {
  private offcanvasService = inject(NgbOffcanvas);

  constructor(
    private router: Router,
    private authService: AuthService,
    private customer: CustomerService
  ) {
    if (authService.isLoggedIn()) {
      this.getname();
    }
  }

  @ViewChild('content') content!: TemplateRef<any>;

  open() {
    this.offcanvasService.open(this.content, {
      ariaLabelledBy: 'offcanvas-basic-title',
    });
  }

  name: string = '';

  buttons = [
    {
      name: 'Generate/Change Credit Card PIN',
      icon: 'fa-solid fa-key',
      link: '/change-creditCard-pin',
    },
    {
      name: 'Generate/Change Debit Card PIN',
      icon: 'fa-solid fa-key',
      link: '/change-debitCard-pin',
    },
    { name: 'Change TPIN', icon: 'fa-solid fa-lock', link: '/change-tpin' },
    {
      name: 'Profile',
      icon: 'fa-solid fa-user-edit',
      link: '/profile',
    },
    {
      name: 'Logout',
      icon: 'fas fa-sign-out-alt',
      link: '/logout',
    },
  ];

  close(): void {
    this.offcanvasService.dismiss();
  }

  navigateTo(link: string): void {
    if (link.includes('logout')) {
      this.authService.logOut();
      this.close();
      this.router.navigate(['auth']);
    } else {
      this.close();
      this.router.navigate([link]);
    }
  }

  getname(): void {
    this.customer.getCurrentCustomerInfo().subscribe({
      next: (response) => {
        this.name = response.name;
        // console.log(response);
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }
}
