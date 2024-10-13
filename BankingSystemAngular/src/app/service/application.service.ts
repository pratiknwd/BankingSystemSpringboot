import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AccountApplication } from '../Model/account-application';
import { Application } from '../Model/application';
import { FinancialProductApplication } from '../Model/financialProduct-application';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root',
})
export class ApplicationService {
  url: string = '';

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.url = auth.getBaseUrl();
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.auth.getJwtToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  submitAccountApplication(
    accountApplication: AccountApplication
  ): Observable<string> {
    const headers = this.getAuthHeaders();
    return this.httpClient.post<string>(
      this.url + '/account-creation-application',
      accountApplication,
      {
        headers,
      }
    );
  }

  submitFinancialProductApplication(
    financialProduct: FinancialProductApplication
  ): Observable<any> {
    const headers = this.getAuthHeaders();

    return this.httpClient.post<any>(this.url + '/apply', financialProduct, {
      headers,
    });
  }

  getAccountApplicationType(): Observable<string[]> {
    const headers = this.getAuthHeaders();

    return this.httpClient.get<string[]>(
      this.url + '/account-application-type',
      { headers }
    );
  }

  getFinancialProductApplicationType(): Observable<string[]> {
    const headers = this.getAuthHeaders();

    return this.httpClient.get<string[]>(
      this.url + '/financial-application-type',
      { headers }
    );
  }

  getCurrentUserApplications(): Observable<Application[]> {
    const headers = this.getAuthHeaders();

    return this.httpClient.get<Application[]>(
      this.url + '/getCurrentUserApplications',
      { headers }
    );
  }
}
