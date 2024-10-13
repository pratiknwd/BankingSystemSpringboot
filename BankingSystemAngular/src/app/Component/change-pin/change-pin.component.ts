import { Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NavigationEnd, Router } from '@angular/router';
import { ChangePin } from 'src/app/Model/change-pin';
import { AccountService } from 'src/app/service/account.service';
import { CardService } from 'src/app/service/card.service';

@Component({
  selector: 'app-change-pin',
  templateUrl: './change-pin.component.html',
  styleUrls: ['./change-pin.component.css']
})
export class ChangePinComponent {
  pinChangeForm!: FormGroup;
  numbers: number[] = [];

  // dynamic dom loading elements
  heading: string = '';
  pinDigit: string = '';
  select: string = '';
  pinType: string = '';
  icon: string = '';
  numberType: string = '';

  showAlert: boolean = false;
  alert = { type: '', message: '' };

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private location: Location,
    private cardService: CardService,
    private accountService: AccountService
  ) {}

  ngOnInit(): void {
    this.pinChangeForm = this.fb.group(
      {
        number: ['', Validators.required],
        newPin: [
          '',
          [
            Validators.required,
            Validators.minLength(4),
            Validators.maxLength(6),
            Validators.pattern(/^\d+$/), // Numeric pattern
          ],
        ],
        confirmPin: ['', Validators.required],
        password: ['', Validators.required],
      },
      { validators: this.matchingPins }
    );

    this.updateDom();

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.updateDom();
      }
    });
  }

  updateDom(): void {
    const url = this.router.url;
    if (url.includes('change-debitCard-pin')) {
      this.numberType = 'Card number';
      this.heading = 'Debit card Pin change';
      this.select = 'Select Card Number';
      this.icon = 'fas fa-credit-card';
      this.pinType = 'PIN';
      this.pinDigit = '*pin should be of 4 digit';
      this.loadDebitCardNumbers();
    } else if (url.includes('change-creditCard-pin')) {
      this.numberType = 'Card number';
      this.heading = 'Credit card Pin change';
      this.select = 'Select Card Number';
      this.icon = 'fas fa-credit-card';
      this.pinType = 'PIN';
      this.pinDigit = '*pin should be of 4 digit';
      this.loadCreditCardNumbers();
    } else {
      this.numberType = 'Account number';
      this.heading = 'T-Pin change';
      this.select = 'Select Account Number';
      this.icon = 'fas fa-university';
      this.pinType = 'TPIN';
      this.pinDigit = '*pin should be of 6 digit';
      this.loadAccountNumbers();
    }
  }

  loadAccountNumbers(): void {
    this.accountService.getAllAccountNumbers().subscribe({
      next: (response) => {
        this.numbers = response;
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  loadDebitCardNumbers(): void {
    this.cardService.getAllDebitCardNumber().subscribe({
      next: (response) => {
        this.numbers = response;
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  loadCreditCardNumbers(): void {
    this.cardService.getAllCreditcardNumber().subscribe({
      next: (response) => {
        this.numbers = response;
      },
      error: (error) => {
        console.log(error.error.message);
      },
    });
  }

  changeFormData(form: FormGroup): ChangePin {
    return {
      number: form.value.number,
      newPin: form.value.newPin,
      password: form.value.password,
    };
  }

  onSubmit(): void {
    if (this.pinChangeForm.valid) {
      const form: ChangePin = this.changeFormData(this.pinChangeForm);
      console.log(form);
      const url = this.router.url;
      if (url.includes('change-debitCard-pin')) {
        this.cardService.changeDebitCardPin(form).subscribe({
          next: (response) => {
            // console.log(response);
            this.alert.type = 'success';
            this.alert.message = response.message;
            this.triggerAlert();
            this.pinChangeForm.reset();
          },
          error: (error) => {
            // console.log(error.error.message);
            this.alert.type = 'danger';
            this.alert.message = error.error.message;
            this.triggerAlert();
            this.pinChangeForm.reset();
          },
        });
      } else if (url.includes('change-creditCard-pin')) {
        this.cardService.changeCreditCardPin(form).subscribe({
          next: (response) => {
            // console.log(response);
            this.alert.type = 'success';
            this.alert.message = response.message;
            this.triggerAlert();
            this.pinChangeForm.reset();
          },
          error: (error) => {
            // console.log(error);
            this.alert.type = 'danger';
            this.alert.message = error.error.message;
            this.triggerAlert();
            this.pinChangeForm.reset();
          },
        });
      } else {
        this.accountService.changeTpin(form).subscribe({
          next: (response) => {
            // console.log(response);
            this.alert.type = 'success';
            this.alert.message = response.message;
            this.triggerAlert();
            this.pinChangeForm.reset();
          },
          error: (error) => {
            // console.log(error);
            this.alert.type = 'danger';
            this.alert.message = error.error.message;
            this.triggerAlert();
            this.pinChangeForm.reset();
          },
        });
      }
    }
  }

  matchingPins(group: FormGroup) {
    const newPin = group.get('newPin')?.value;
    const confirmPin = group.get('confirmPin')?.value;
    return newPin === confirmPin ? null : { matchingPins: true };
  }

  triggerAlert() {
    this.showAlert = true;

    setTimeout(() => {
      this.showAlert = false;
    }, 4000);
  }

  goBack(): void {
    this.location.back();
  }

}
