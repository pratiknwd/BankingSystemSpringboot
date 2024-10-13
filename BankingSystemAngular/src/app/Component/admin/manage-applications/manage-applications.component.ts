import { Component } from '@angular/core';

@Component({
  selector: 'app-manage-applications',
  templateUrl: './manage-applications.component.html',
  styleUrls: ['./manage-applications.component.css'],
})
export class ManageApplicationsComponent {
  applicationFunctionalities = [
    {
      title: 'Account Application',
      icon: 'fas fa-user-check',
      description: 'View and manage account applications within the system.',
      link: '/admin/pending-account-applications',
      buttonText: 'Account Applications',
    },
    {
      title: 'Loans Application',
      icon: 'fas fa-file-invoice-dollar',
      description: 'View and manage loan applications within the system.',
      link: '/admin/pending-loan-applications',
      buttonText: 'Loan Applications',
    },
    {
      title: 'Credit Card Application',
      icon: 'fas fa-credit-card',
      description:
        'View and manage credit card applications within the system.',
      link: '/admin/pending-creditCard-applications',
      buttonText: 'Credit Card Applications',
    },
    {
      title: 'Beneficiary Application',
      icon: 'fas fa-users',
      description:
        'View and manage beneficiary applications within the system.',
      link: '/admin/pending-beneficiary-applications',
      buttonText: 'Beneficiary Applications',
    },
    {
      title: 'Rejected Application',
      icon: 'fas fa-ban',
      description: 'View and manage rejected applications within the system.',
      link: '/admin/rejected-applications',
      buttonText: 'Rejected Applications',
    },
    {
      title: 'Rejected Beneficiaries',
      icon: 'fas fa-ban',
      description: 'View and manage rejected Beneficiaries.',
      link: '/admin/rejected-beneficiaries',
      buttonText: 'Rejected Beneficiaries',
    },
  ];
}
