import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { ApplicationType } from 'src/app/Model/admin/application-type.model';

@Injectable({
  providedIn: 'root'
})
export class ApplicationTypeService {
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

  getAllApplicationTypes(): Observable<ApplicationType[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<ApplicationType[]>(`${this.url}/admin/applicationType/getAllApplicationTypes`, { headers });
  }

  addApplicationType(applicationType: string): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.post<any>(
      `${this.url}/admin/applicationType/addApplicationType?applicationType=${applicationType}`, 
      {}, 
      { headers }
    );
  }

  updateApplicationType(applicationTypeId: number, applicationType: string): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      `${this.url}/admin/applicationType/updateApplicationType/${applicationTypeId}?type=${applicationType}`, 
      {}, 
      { headers }
    );
  }
  
}
