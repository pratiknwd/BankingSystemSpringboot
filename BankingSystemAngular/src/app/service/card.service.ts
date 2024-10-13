import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Card } from '../Model/cards';
import { ChangePin } from '../Model/change-pin';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class CardService {
  Url: string = '';

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.Url = this.auth.getBaseUrl();
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.auth.getJwtToken(); // Fetch the token at the time of each request
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  getAllcards(): Observable<Card[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<Card[]>(this.Url + '/cards', { headers });
  }

  getAllDebitCardNumber(): Observable<number[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<number[]>(this.Url + '/getDebitCardNumbers', {
      headers,
    });
  }

  getAllCreditcardNumber(): Observable<number[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<number[]>(this.Url + '/getCreditCardNumbers', {
      headers,
    });
  }

  changeDebitCardPin(changeDebitPin: ChangePin): Observable<any> {
    const headers = this.getAuthHeaders();

    // console.log(changeDebitPin);

    return this.httpClient.put<any>(
      this.Url + '/debitCard/changePin',
      changeDebitPin,
      {
        headers,
      }
    );
  }

  changeCreditCardPin(changeCreditPin: ChangePin): Observable<any> {
    const headers = this.getAuthHeaders();

    return this.httpClient.put<any>(
      this.Url + '/creditCard/changePin',
      changeCreditPin,
      { headers }
    );
  }
}
