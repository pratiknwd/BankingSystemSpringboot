import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'cardNumberFormater'
})
export class CardNumberFormaterPipe implements PipeTransform {

  transform(cardNumber: number): string {
    // Convert number to string
    const cardNumberStr = cardNumber.toString();
    
    // Ensure the input is a string of 16 digits
    if (!/^\d{16}$/.test(cardNumberStr)) {
      return cardNumberStr; // Return the input if it's not a valid 16-digit number
    }

    // Format the number: "XXXX XXXX XXXX XXXX"
    return cardNumberStr.replace(/(.{4})/g, '$1 ');
  }

}
