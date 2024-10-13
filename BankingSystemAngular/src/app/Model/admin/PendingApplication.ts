import { Customer } from '../customer';

export interface PendingApplicaiton {
  applicationId: number;
  customer: Customer;
  applicationType: string;
  applicationNumber: number;
  dateOfApplication: string;
  applicationStatus: string;
  branchIfsc: string | null;
}
