import { Address } from './address';

export interface RegisterCustomer {
  name: string;
  addressDto: Address;
  gender:string,
  dateOfBirth: string;
  phoneNumber: string;
}
