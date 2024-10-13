import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Branch } from 'src/app/Model/branch';
import { AuthService } from '../auth.service';
import { EditBranch } from 'src/app/Model/admin/editBranch';

@Injectable({
  providedIn: 'root',
})
export class BranchService {
  private url: string;

  constructor(
    private httpClient: HttpClient,
    private authService: AuthService
  ) {
    this.url = `${this.authService.getBaseUrl()}/admin/branch`;
  }

  private getAuthHeaders(): HttpHeaders {
    const token = this.authService.getJwtToken();
    return new HttpHeaders({
      Authorization: `Bearer ${token}`,
      'Content-Type': 'application/json',
    });
  }

  addBranch(branchData: any): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.post<any>(`${this.url}/addBranch`, branchData, {
      headers,
    });
  }

  getAllBranches(): Observable<any[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<any[]>(`${this.url}/getAllBranches`, {
      headers,
    });
  }

  getBranchById(branchId: string): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<any>(`${this.url}/getBranch/${branchId}`, {
      headers,
    });
  }

  deleteBranch(branchId: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.delete<any>(`${this.url}/deleteBranch/${branchId}`, {
      headers,
    });
  }

  updateBranch(branchId: number, branch: EditBranch): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      `${this.url}/updateBranch/${branchId}`,
      branch,
      { headers }
    );
  }
}
