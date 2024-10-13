import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { DebitCardType } from 'src/app/Model/admin/debit-card-type.model';

@Injectable({
  providedIn: 'root',
})
export class CardTypeService {
  private url: string;

  constructor(private http: HttpClient, private authService: AuthService) {
    this.url = this.authService.getBaseUrl();
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.authService.getJwtToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }

  getDebitCardTypes(): Observable<DebitCardType[]> {
    const headers = this.getAuthHeaders();
    return this.http.get<DebitCardType[]>(`${this.url}/admin/cardtype/debitcardtypes`, { headers });
  }

  addDebitCardType(debitCardType: DebitCardType): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.http.post(`${this.url}/admin/cardtype/addDebitCardType`, debitCardType, { headers });
  }
}
