import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { TransactionType } from '../../Model/transaction-type';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root',
})
export class TransactionTypeChannelService {
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

  // TransactionTypes service
  getAllTransactionTypes(): Observable<TransactionType[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<TransactionType[]>(
      this.url + '/allTransactionTypes',
      {
        headers,
      }
    );
  }

  deleteTransactionType(type: string): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.delete(
      this.url + `/admin/deleteTransactionType?transactionType=${type}`,
      { headers }
    );
  }

  addTransactionType(type: string): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.post<any>(
      this.url + `/admin/addTransactionType?transactionType=${type}`,
      {},
      { headers }
    );
  }

  //TransactionChannel service
  getTransactionChannels(): Observable<any> {
    const headers = this.getAuthHeaders();

    return this.httpClient.get(this.url + `/allTransactionChannels`, {
      headers,
    });
  }

  addTransactionChannel(channel: string): Observable<any> {
    const headers = this.getAuthHeaders();

    return this.httpClient.post<any>(
      this.url + `/admin/addTransactionChannel?transactionChannel=${channel}`,
      {},
      { headers }
    );
  }

  deleteTransactionChannel(channel: string): Observable<any> {
    const headers = this.getAuthHeaders();

    return this.httpClient.delete(
      this.url +
        `/admin/deleteTransactionChannel?transactionChannel=${channel}`,
      { headers }
    );
  }
}
