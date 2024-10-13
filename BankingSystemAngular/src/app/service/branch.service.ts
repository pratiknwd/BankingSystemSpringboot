import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable } from 'rxjs';
import { Branch } from '../Model/branch';

@Injectable({
  providedIn: 'root',
})
export class BranchService {
  baseUrl: string = '';

  constructor(private httpClient: HttpClient, private auth: AuthService) {
    this.baseUrl = this.auth.getBaseUrl();
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.auth.getJwtToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
    });
  }

  getAllBranch(): Observable<Branch[]> {
    const headers = this.getAuthHeaders();

    return this.httpClient.get<Branch[]>(
      this.baseUrl + '/admin/branch/getAllBranches',
      { headers }
    );
  }

  getAllIfsc(): Observable<string[]> {
    const headers = this.getAuthHeaders();

    return this.httpClient.get<string[]>(
      this.baseUrl + '/admin/branch/getAllIfsc',
      {
        headers,
      }
    );
  }
}
