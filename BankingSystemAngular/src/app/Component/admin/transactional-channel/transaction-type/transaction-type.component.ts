import { Component, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { TransactionTypeChannelService } from 'src/app/service/admin/transaction-type-channel.service';

@Component({
  selector: 'app-transaction-type',
  templateUrl: './transaction-type.component.html',
  styleUrls: ['./transaction-type.component.css'],
})
export class TransactionTypeComponent {
  transactionTypes: any[] = [];
  selectedType: any = null;

  @ViewChild('addModal') addModal: any; 
  @ViewChild('deleteModal') deleteModal: any; 

  constructor(
    private transactionService: TransactionTypeChannelService,
    private modalService: NgbModal 
  ) {}

  ngOnInit(): void {
    this.loadTransactionTypes();
  }

  loadTransactionTypes() {
    this.transactionService.getAllTransactionTypes().subscribe((data) => {
      this.transactionTypes = data;
    });
  }

 
  openAddModal() {
    this.modalService.open(this.addModal);
  }

  
  openDeleteModal(type: any) {
    this.selectedType = type;
    this.modalService.open(this.deleteModal);
  }


  deleteTransactionType() {
    this.transactionService
      .deleteTransactionType(this.selectedType.transactionMethod)
      .subscribe(() => {
        this.loadTransactionTypes();
        this.modalService.dismissAll();
      });
  }

  
  addTransactionType(newType: string) {
    this.transactionService.addTransactionType(newType).subscribe(() => {
      this.loadTransactionTypes();
      this.modalService.dismissAll(); 
    });
  }
}
