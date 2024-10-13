import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from 'src/app/Model/account';
import { PendingApplicaiton } from 'src/app/Model/admin/PendingApplication';
import { Beneficiary } from 'src/app/Model/beneficiary';
import { AuthService } from '../auth.service';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  baseUrl: string = '';

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.baseUrl = auth.getBaseUrl() + '/admin';
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.auth.getJwtToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  getAccountPendingApplications(): Observable<PendingApplicaiton[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<PendingApplicaiton[]>(
      this.baseUrl + '/accounts-pending-applications',
      { headers }
    );
  }

  getLoanPendingApplications(): Observable<PendingApplicaiton[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<PendingApplicaiton[]>(
      this.baseUrl + '/loans-pending-applications',
      { headers }
    );
  }

  getCreditCardPendingApplications(): Observable<PendingApplicaiton[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<PendingApplicaiton[]>(
      this.baseUrl + '/creditCards-pending-applications',
      { headers }
    );
  }

  getAccountByAccountNumber(accountNumber: number): Observable<Account> {
    const headers = this.getAuthHeaders();

    return this.httpClient.get<Account>(
      this.baseUrl + `/account/${accountNumber}`,
      { headers }
    );
  }

  getBlockedAccounts(): Observable<Account[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<Account[]>(this.baseUrl + '/blocked-accounts', {
      headers,
    });
  }

  getRejectedApplications(): Observable<PendingApplicaiton[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<PendingApplicaiton[]>(
      this.baseUrl + '/rejected-application',
      { headers }
    );
  }

  getBeneficieriesPendingApplication(): Observable<Beneficiary[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<Beneficiary[]>(
      this.baseUrl + '/beneficiaries_pending_for_approval',
      {
        headers,
      }
    );
  }

  getBeneficiariesRejectedApplications(): Observable<Beneficiary[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<Beneficiary[]>(
      this.baseUrl + '/beneficiaries-rejected-application',
      {
        headers,
      }
    );
  }

  rejectApplication(applicationNumber: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      this.baseUrl + `/reject/${applicationNumber}`,
      {},
      { headers }
    );
  }

  rejectBeneficiary(beneficiaryId: number): Observable<any> {
    const headers = this.getAuthHeaders();

    return this.httpClient.put<any>(
      this.baseUrl + `/reject_beneficiary/${beneficiaryId}`,
      {},
      { headers }
    );
  }

  approveAccountApplication(applicationNumber: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      this.baseUrl + `/approve-account/${applicationNumber}`,
      {},
      { headers }
    );
  }

  approveCreditCardApplication(applicationNumber: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      this.baseUrl + `/approve-credit-cards/${applicationNumber}`,
      {},
      { headers }
    );
  }

  approveLoanApplication(applicationNumber: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      this.baseUrl + `/approve-loan/${applicationNumber}`,
      {},
      { headers }
    );
  }

  approveBeneficiaries(beneficiaryId: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      this.baseUrl + `/approve_beneficiary/${beneficiaryId}`,
      {},
      { headers }
    );
  }

  blockAccount(accountNumber: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      this.baseUrl + `/${accountNumber}/change-account-status-blocked`,
      {},
      { headers }
    );
  }

  unBlockAccount(accountNumber: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      this.baseUrl + `/${accountNumber}/change-account-status-active`,
      {},
      { headers }
    );
  }
}
