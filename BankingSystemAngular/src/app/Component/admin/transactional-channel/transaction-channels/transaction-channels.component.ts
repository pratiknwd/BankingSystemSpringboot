import { Component, ViewChild } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap'; // Import for modals
import { TransactionTypeChannelService } from 'src/app/service/admin/transaction-type-channel.service';

@Component({
  selector: 'app-transaction-channels',
  templateUrl: './transaction-channels.component.html',
  styleUrls: ['./transaction-channels.component.css'],
})
export class TransactionChannelsComponent {
  transactionChannels: any[] = [];
  selectedType: any = null; 
  @ViewChild('addModal') addModal: any; 
  @ViewChild('deleteModal') deleteModal: any; 

  constructor(
    private transactionService: TransactionTypeChannelService,
    private modalService: NgbModal 
  ) {}

  ngOnInit(): void {
    this.loadTransactionChannels();
  }

  loadTransactionChannels() {
    this.transactionService.getTransactionChannels().subscribe((data) => {
      this.transactionChannels = data;
    });
  }

  
  openAddModal() {
    this.modalService.open(this.addModal);
  }

  
  openDeleteModal(type: any) {
    this.selectedType = type; 
    this.modalService.open(this.deleteModal);
  }

  
  deleteTransactionChannel(channelId: string) {
    this.transactionService.deleteTransactionChannel(channelId).subscribe(() => {
      this.loadTransactionChannels();
    });
  }

  
  addTransactionChannel(newType: string) {
    this.transactionService.addTransactionChannel(newType).subscribe(() => {
      this.loadTransactionChannels();
    });
  }
}
