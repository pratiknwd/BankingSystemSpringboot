import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateFormator'
})
export class DateFormatorPipe implements PipeTransform {

  transform(dateString: string): string {
    const date = new Date(dateString);
    
    // Check if the date is valid
    if (isNaN(date.getTime())) {
      return dateString; // Return the input if it's not a valid date
    }
    
    // Extract month and year, format them as MM/YY
    const month = ('0' + (date.getMonth() + 1)).slice(-2); // Ensure two digits for month
    const year = date.getFullYear().toString().slice(-2);   // Get the last two digits of the year
    
    return `${month}/${year}`;
  }

}
