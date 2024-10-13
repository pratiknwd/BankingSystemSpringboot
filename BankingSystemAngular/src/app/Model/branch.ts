import { Address } from './address';

export interface Branch {
  branchId: number;
  address: Address;
  ifsc: string;
  createdDate: string;
}
