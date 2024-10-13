import { Branch } from './branch';
import { Customer } from './customer';

export interface Account {
  accountId: number;
  customer: Customer;
  branch: Branch;
  accountType: string;
  accountNumber: number;
  balance: number;
  accountStatus: string;
  tpin: string |null;
}
