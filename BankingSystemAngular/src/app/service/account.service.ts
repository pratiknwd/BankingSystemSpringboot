import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../Model/account';
import { ChangePin } from '../Model/change-pin';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class AccountService {
  url: string = '';

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.url = this.auth.getBaseUrl() + '/account';
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.auth.getJwtToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  getAllAccount(): Observable<Account[]> {
    const headers = this.getAuthHeaders();

    return this.httpClient.get<Account[]>(this.url + '/account-details', {
      headers,
    });
  }

  getAllAccountNumbers(): Observable<number[]> {
    const headers = this.getAuthHeaders();

    return this.httpClient.get<number[]>(this.url + '/getAccountNumbers', {
      headers,
    });
  }

  changeTpin(form: ChangePin): Observable<any> {
    const headers = this.getAuthHeaders();

    return this.httpClient.put<any>(this.url + '/changeTpin', form, {
      headers,
    });
  }
}
