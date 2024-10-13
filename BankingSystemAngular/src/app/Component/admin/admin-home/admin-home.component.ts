import { Component } from '@angular/core';
import { ConfigService } from 'src/app/app-config';

@Component({
  selector: 'app-admin-home',
  templateUrl: './admin-home.component.html',
  styleUrls: ['./admin-home.component.css'],
})
export class AdminHomeComponent {
  cards = [
    {
      title: 'View All Banks',
      icon: 'fas fa-university',
      description: 'See a list of all registered banks.',
      link: '/admin/view-banks',
      buttonText: 'View Banks',
    },
    {
      title: 'Accounts',
      icon: 'fas fa-user-check',
      description: 'Manage Accounts',
      link: '/admin/manage-accounts',
      buttonText: 'Verify Account',
    },
    {
      title: 'Manage Applications',
      icon: 'fas fa-clipboard-list',
      description: 'View, manage, and process applications in the system.',
      link: '/admin/manage-applications',
      buttonText: 'Manage Applications',
    },
    {
      title: 'Types/Channels',
      icon: 'fas fa-search',
      description: 'Manage Transaction Types/Channel.',
      link: '/admin/transacation-channel',
      buttonText: 'Manage ',
    },
    {
      title: 'Manage Roles',
      icon: 'fas fa-clipboard-list',
      description: 'View, manage, and Add Roles in the system.',
      link: '/admin/manage-roles',
      buttonText: 'Manage Roles',
    },
    {
      title: 'Manage Loan Types',
      icon: 'fas fa-clipboard-list',
      description: 'View, manage, and Add New Loan Types in the system.',
      link: '/admin/manage-loan-type',
      buttonText: 'Manage Loan Types',
    },
    {
      title: 'Manage Document Types',
      icon: 'fas fa-clipboard-list',
      description: 'View, manage, and Add New Document Types in the system.',
      link: '/admin/manage-document-type',
      buttonText: 'Manage Document Types',
    },
    {
      title: 'Manage Application Types',
      icon: 'fas fa-clipboard-list',
      description: 'View, manage, and Add New Application Types in the system.',
      link: '/admin/manage-application-type',
      buttonText: 'Manage Application Types',
    },
  ];
}
