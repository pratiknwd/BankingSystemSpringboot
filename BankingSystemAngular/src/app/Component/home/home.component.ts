import { Component, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ApplicationService } from 'src/app/service/application.service';
import { SettingsComponent } from '../settings/settings.component';
import { AlertComponent } from '../alert/alert.component';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent {
  @ViewChild(SettingsComponent) settingsComponent!: SettingsComponent;
  @ViewChild(AlertComponent) alertComponent!: AlertComponent;

  private isAccountPresent: boolean = false;
  private isCardPresent: boolean = false;

  constructor(
    private applicationService: ApplicationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.applicationService.getCurrentUserApplications().subscribe({
      next: (response) => {
        if (response.length > 0) {
          response.forEach((application) => {
            if (
              application.applicationType.toLowerCase().includes('account') &&
              application.applicationStatus === 'Approved'
            ) {
              this.isAccountPresent = true;
            }
            if (
              (application.applicationType
                .toLowerCase()
                .includes('creditcard') ||
                application.applicationType
                  .toLowerCase()
                  .includes('account')) &&
              application.applicationStatus === 'Approved'
            ) {
              this.isCardPresent = true;
            }
          });
        }
      },
    });
  }

  openSettings() {
    this.settingsComponent.open();
  }

  cards: any = [
    {
      icon: 'fas fa-user-circle',
      title: 'Profile',
      text: 'View and manage your profile information.',
      link: '/profile',
      button_text: 'DashBoard',
      serviceRelatedTo: 'general',
    },
    {
      icon: 'fas fa-exchange-alt',
      title: 'Transfer Money',
      text: 'Transfer money to other accounts.',
      link: '/moneyTransfer',
      button_text: 'Transfer Money',
      serviceRelatedTo: 'account',
    },
    {
      icon: 'fas fa-balance-scale',
      title: 'Check Balance',
      text: 'Check Balance',
      link: '/accountBalance',
      button_text: 'Check Balance',
      serviceRelatedTo: 'account',
    },
    {
      icon: 'fas fa-user-plus',
      title: 'Add Beneficiaries',
      text: 'Add new beneficiaries',
      link: '/beneficiaries/add',
      button_text: 'Add Beneficiary',
      serviceRelatedTo: 'account',
    },
    {
      icon: 'fas fa-users',
      title: 'View Beneficiaries',
      text: 'View your beneficiaries',
      link: '/beneficiaries/view',
      button_text: 'View Beneficiaries',
      serviceRelatedTo: 'account',
    },
    {
      icon: 'fas fa-credit-card',
      title: 'Cards',
      text: 'Credit/Debit cards',
      link: '/cards',
      button_text: 'Cards',
      serviceRelatedTo: 'card',
    },
    {
      icon: 'fas fa-folder-open',
      title: 'View Applications',
      text: 'View all applications',
      link: '/application/view',
      button_text: 'View Applications',
      serviceRelatedTo: 'general',
    },
    {
      icon: 'fas fa-user-plus',
      title: 'Create new Account',
      text: 'New Account Type Creation',
      link: '/application/account',
      button_text: 'Create Account',
      serviceRelatedTo: 'general',
    },
    {
      icon: 'fas fa-plus-square',
      title: 'Loan/Credit Card',
      text: 'Apply for Loan/Credit card',
      link: '/application/apply',
      button_text: 'Apply',
      serviceRelatedTo: 'general',
    },
    {
      icon: 'fas fa-file-alt',
      title: 'Statements',
      text: 'Transactions Statement',
      link: '/accountStatement',
      button_text: 'Statement',
      serviceRelatedTo: 'account',
    },
    {
      icon: 'fas fa-toolbox',
      title: 'Financial Tools',
      text: 'Financial Tools',
      link: '/financialtools',
      button_text: 'Financial Tools',
      serviceRelatedTo: 'general',
    },
    {
      icon: 'fas fa-gear',
      title: 'Setting',
      text: 'Change Pin / Update Info',
      link: '/settings',
      button_text: 'Setting',
      serviceRelatedTo: 'account',
    },
  ];

  navigateTo(card: any): void {
    if (card.serviceRelatedTo === 'account') {
      if (this.isAccountPresent) {
        this.router.navigate([card.link]);
      } else {
        this.triggerAlert('info', 'Apply for an account to use this feature.');
      }
    } else if (card.serviceRelatedTo === 'card') {
      if (this.isCardPresent) {
        this.router.navigate([card.link]);
      } else {
        this.triggerAlert(
          'info',
          'Apply For a account/CerditCard to use this Feature...!'
        );
      }
    } else {
      this.router.navigate([card.link]);
    }
  }

  triggerAlert(type: string, message: string) {
    this.alertComponent.alertType = type;
    this.alertComponent.alertMessage = message;
    this.alertComponent.triggerAlert();
  }
}
