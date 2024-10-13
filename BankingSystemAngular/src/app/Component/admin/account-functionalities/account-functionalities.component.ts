import { Component } from '@angular/core';

@Component({
  selector: 'app-account-functionalities',
  templateUrl: './account-functionalities.component.html',
  styleUrls: ['./account-functionalities.component.css'],
})
export class AccountFunctionalitiesComponent {
  accountFunctionalities = [
    {
      title: 'Account Pending Application',
      icon: 'fas fa-hourglass-half',
      description: 'View and manage pending account applications.',
      link: '/admin/pending-account-applications',
      buttonText: 'Pending Accounts',
    },
    {
      title: 'Block/Unblock Account',
      icon: 'fas fa-lock',
      description: 'Manage account status by blocking or unblocking accounts.',
      link: '/admin/block-unblock-accounts',
      buttonText: 'Block/Unblock',
    },

    {
      title: 'Get All Blocked Accounts',
      icon: 'fas fa-ban',
      description: 'Retrieve a list of all blocked accounts.',
      link: '/admin/blocked-accounts',
      buttonText: 'Blocked Accounts',
    },
    {
      title: 'Beneficiary Pending Application',
      icon: 'fas fa-clock',
      description: 'View and manage pending beneficiary applications.',
      link: '/admin/pending-beneficiary-applications',
      buttonText: 'Pending Beneficiaries',
    },
  ];
}
