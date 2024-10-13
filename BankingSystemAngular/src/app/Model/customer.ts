import { Address } from './address';
import { User } from './user';

export interface Customer {
  customerId: number;
  user: User;
  name: string;
  gender:string;
  address: Address;
  dateOfBirth: string;
  phoneNumber: string;
}
