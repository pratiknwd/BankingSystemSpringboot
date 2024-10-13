import { Component } from '@angular/core';

@Component({
  selector: 'app-all-tools',
  templateUrl: './all-tools.component.html',
  styleUrls: ['./all-tools.component.css'],
})
export class AllToolsComponent {
  tools = [
    {
      title: 'CI Calculator',
      description:
        'Calculate your compound interest with ease using our CI Calculator.',
      icon: 'fas fa-chart-line',
      link: '/financialtools/ciCalculator',
    },
    {
      title: 'GST Calculator',
      description:
        'Quickly determine your GST with our easy-to-use GST Calculator.',
      icon: 'fas fa-receipt',
      link: '/financialtools/gstCalculator',
    },
    {
      title: 'SIP Calculator',
      description:
        'Plan your SIP investments with our comprehensive SIP Calculator.',
      icon: 'fas fa-hand-holding-usd',
      link: '/financialtools/sipCalculator',
    },
    {
      title: 'PPF Calculator',
      description:
        'Estimate your PPF returns with our efficient PPF Calculator.',
      icon: 'fas fa-piggy-bank',
      link: '/financialtools/ppfCalculator',
    },
  ];
}
