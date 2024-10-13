
export interface Application {
  applicationId: number;
  applicationType: string;
  applicationNumber: number;
  dateOfApplication: string;
  ifsc: string | null;
  applicationStatus: string;
}
