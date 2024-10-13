import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dateTimeFormater'
})
export class DateTimeFormaterPipe implements PipeTransform {

  transform(value: Date | string, ...args: unknown[]): string {
    if (!value) {
      return '';
    }

    const date = new Date(value);

    // Check if the date is invalid
    if (isNaN(date.getTime())) {
      return 'Invalid date';
    }

    // Format the date using Intl.DateTimeFormat
    const dayName = new Intl.DateTimeFormat('en-US', { weekday: 'long' }).format(date);
    const monthName = new Intl.DateTimeFormat('en-US', { month: 'long' }).format(date);
    const day = new Intl.DateTimeFormat('en-US', { day: 'numeric' }).format(date);
    const time = new Intl.DateTimeFormat('en-US', { hour: 'numeric', minute: 'numeric', hour12: true }).format(date);

    return `${dayName}, ${monthName} ${day} at ${time}`;
  }
}
