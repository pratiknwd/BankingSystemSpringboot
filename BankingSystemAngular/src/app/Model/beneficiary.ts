import { Customer } from './customer';

export interface Beneficiary {
  beneficiaryId: number;
  customer: Customer;
  beneficiaryAccountNumber: number;
  beneficiaryName: string;
  beneficiaryBankName: string;
  beneficiaryIFSC: string;
  beneficiaryStatus: string;
}
