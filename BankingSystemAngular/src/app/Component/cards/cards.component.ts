import { Location } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { Card } from 'src/app/Model/cards';
import { Customer } from 'src/app/Model/customer';
import { CardService } from 'src/app/service/card.service';
import { CustomerService } from 'src/app/service/customer.service';

@Component({
  selector: 'app-cards',
  templateUrl: './cards.component.html',
  styleUrls: ['./cards.component.css'],
})
export class CardsComponent implements OnInit{
  Cards: Card[] = [];
  userName!:string;
  currentCustomer: Customer = {
    customerId: 0,
    user: {
      username: '',
      email: '',
    },
    name: '',
    address: {
      buildingName: '',
      street: '',
      city: '',
      state: '',
      country: '',
      zipcode: 0,
    },
    dateOfBirth: '',
    phoneNumber: '',
    gender: ''
  };

  constructor(
    config: NgbCarouselConfig,
    private cardService: CardService,
    private customerService: CustomerService,
    private location:Location
  ) {
    config.interval = 10000;
    config.wrap = false;
    config.keyboard = false;
    config.pauseOnHover = false;
  }

  ngOnInit(): void {
    this.loadAllCards();
    this.loadCurrentCustomerInfo();
  }

  loadCurrentCustomerInfo():void{
    this.customerService.getCurrentCustomerInfo().subscribe({
      next:(response)=>{
        this.currentCustomer=response;
        this.userName=response.name;
        // console.log(response,this.userName);
      },
      error:(error)=>{
        console.log(error.eroor.message);
      }
    });
  }

  loadAllCards(): void {
    this.cardService.getAllcards().subscribe({
      next: (response) => {
        this.Cards = response;
        // console.log(response);
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  goBack():void{
    this.location.back();
  }
}
