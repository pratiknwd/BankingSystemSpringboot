import { Component, Input } from '@angular/core';
import { Card } from 'src/app/Model/cards';

@Component({
  selector: 'app-master-cc',
  templateUrl: './master-cc.component.html',
  styleUrls: ['./master-cc.component.css']
})
export class MasterCCComponent {
  @Input() CreditCard:Card={
    cardNumber: 0,
    cvv: 0,
    cardPin: 0,
    expiryDate: '',
    issueDate: '',
    cardType: ''
  };
  @Input() userName!: string;

}
