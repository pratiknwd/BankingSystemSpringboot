import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateOnly',
})
export class DateFromDataandTimePipe implements PipeTransform {
  transform(value: string): string {
    const datePart = value.split(' ').slice(0, 4).join(' ');

    const date = new Date(datePart);

    if (isNaN(date.getTime())) {
      return value;
    }
    const options: Intl.DateTimeFormatOptions = {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
    };

    return date.toLocaleDateString('en-US', options);
  }
}
