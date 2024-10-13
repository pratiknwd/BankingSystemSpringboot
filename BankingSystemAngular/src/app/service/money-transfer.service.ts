import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { MoneyTransfer } from '../Model/money-transfer';
import { Transactions } from '../Model/transactions';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class MoneyTransferService {
  url: string = '';

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.url = this.auth.getBaseUrl();
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.auth.getJwtToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  transferFund(transfer: MoneyTransfer): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.post<any>(this.url + '/transfer_funds', transfer, {
      headers,
    });
  }

  showTransactions(accountNumber: number): Observable<Transactions[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<Transactions[]>(
      `${this.url}/show_transactions?accountNumber=${accountNumber}`,
      { headers }
    );
  }
}
