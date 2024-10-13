import { Address } from './address';

export interface EditCustomer {
  name: string;
  email: string;
  address: Address;
  gender: string;
  phoneNumber: string;
}
