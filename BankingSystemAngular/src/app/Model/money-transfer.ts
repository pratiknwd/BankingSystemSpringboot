export interface MoneyTransfer {
  accountNumber: number;
  beneficiaryAccountNumber: number;
  transactionType: string;
  ransactionChannel: string;
  amount: number;
  remark: string;
  tPin: number;
}
