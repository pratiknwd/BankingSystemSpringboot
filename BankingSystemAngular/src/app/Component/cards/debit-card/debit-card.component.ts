import { Component, Input } from '@angular/core';
import { Card } from 'src/app/Model/cards';
import { CardService } from 'src/app/service/card.service';

@Component({
  selector: 'app-debit-card',
  templateUrl: './debit-card.component.html',
  styleUrls: ['./debit-card.component.css'],
})
export class DebitCardComponent {
  @Input() userName!:string;

  profileIcon: string =
    'https://cdn-icons-png.flaticon.com/512/1436/1436392.png';
  brandLogo: string =
    'https://seeklogo.com/images/V/VISA-logo-DD37676279-seeklogo.com.png';

  @Input() DebitCard: Card = {
    cardNumber: 0,
    cvv: 0,
    cardPin: 0,
    expiryDate: '',
    issueDate: '',
    cardType: '',
  };
}
