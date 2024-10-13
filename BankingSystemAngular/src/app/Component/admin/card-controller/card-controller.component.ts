import { Component, OnInit } from '@angular/core';
import { DebitCardType } from 'src/app/Model/admin/debit-card-type.model';
import { CardTypeService } from 'src/app/service/admin/card-type.service';


@Component({
  selector: 'app-card-controller',
  templateUrl: './card-controller.component.html',
  styleUrls: ['./card-controller.component.css']
})
export class CardControllerComponent implements OnInit {
  debitCardTypes: DebitCardType[] = [];
  newDebitCardType: DebitCardType = new DebitCardType('', 0, 0, 0);

  constructor(private cardTypeService: CardTypeService) {}

  ngOnInit(): void {
    this.getAllDebitCardTypes();
  }

  getAllDebitCardTypes() {
    this.cardTypeService.getDebitCardTypes().subscribe((data: DebitCardType[]) => {
      this.debitCardTypes = data;
    });
  }

  addDebitCardType() {
    if (this.newDebitCardType.debitCardType) {
      this.cardTypeService.addDebitCardType(this.newDebitCardType).subscribe(() => {
        this.getAllDebitCardTypes();
        this.newDebitCardType = new DebitCardType('', 0, 0, 0);
      });
    }
  }
}
