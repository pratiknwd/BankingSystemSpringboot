import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Beneficiary } from '../Model/beneficiary';
import { Customer } from '../Model/customer';
import { RegisterCustomer } from '../Model/registerCustomer';
import { AuthService } from './auth.service';
import { AddBeneficiary } from '../Model/add-beneficiary';
import { EditCustomer } from '../Model/edit-customer';

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  Url: string = '';

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.Url = this.auth.getBaseUrl();
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.auth.getJwtToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  getCurrentCustomerInfo(): Observable<Customer> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<Customer>(this.Url + '/customerInfo', {
      headers,
    });
  }

  updateCustomer(editDetails: EditCustomer): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      this.Url + '/customer-update',
      editDetails,
      { headers }
    );
  }

  registerCustomer(customer: RegisterCustomer): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.post<any>(this.Url + '/registerCustomer', customer, {
      headers,
    });
  }

  addBeneficiary(beneficiary: AddBeneficiary): Observable<any> {
    const headers = this.getAuthHeaders();

    return this.httpClient.post<any>(
      this.Url + '/addBeneficiary',
      beneficiary,
      { headers }
    );
  }

  isCustomerPresent(): Observable<any> {
    const headers = this.getAuthHeaders();
    // console.log(this.getAuthHeaders());
    return this.httpClient.get<any>(this.Url + '/isCustomerDetailPresent', {
      headers,
    });
  }

  getBeneficieries(): Observable<Beneficiary[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<Beneficiary[]>(this.Url + '/beneficiaries', {
      headers,
    });
  }
}
