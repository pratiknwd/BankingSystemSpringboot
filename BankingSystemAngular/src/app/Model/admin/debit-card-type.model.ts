export class DebitCardType {
    debitCardType: string;
    dailyLimit: number;
    weeklyLimit: number;
    monthlyLimit: number;
  
    constructor(debitCardType: string, dailyLimit: number, weeklyLimit: number, monthlyLimit: number) {
      this.debitCardType = debitCardType;
      this.dailyLimit = dailyLimit;
      this.weeklyLimit = weeklyLimit;
      this.monthlyLimit = monthlyLimit;
    }
  }
  