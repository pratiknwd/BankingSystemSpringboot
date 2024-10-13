import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { LoanType } from 'src/app/Model/admin/loan-type.model';

@Injectable({
  providedIn: 'root'
})
export class LoanTypeService {
  url: string = '';

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.url = this.auth.getBaseUrl();
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.auth.getJwtToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`
    });
  }

  getAllLoanTypes(): Observable<LoanType[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<LoanType[]>(`${this.url}/admin/loanType/getAllLoanTypes`, { headers });
  }

  addLoanType(loanType: Partial<LoanType>): Observable<any> {
    console.log(loanType)
    const headers = this.getAuthHeaders();
    return this.httpClient.post<any>(`${this.url}/admin/loanType/addLoanType`, loanType, { headers });
  }

  updateLoanType(loanTypeId: number, loanType: Partial<LoanType>): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(`${this.url}/admin/loanType/updateLoanType/${loanTypeId}`, loanType, { headers });
  }

  deleteLoanType(loanTypeId: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.delete<any>(`${this.url}/admin/loanType/deleteLoanType/${loanTypeId}`, { headers });
  }
}
