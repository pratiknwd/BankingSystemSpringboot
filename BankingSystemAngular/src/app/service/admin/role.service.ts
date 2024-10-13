import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { Role } from 'src/app/Model/admin/role.model';

@Injectable({
  providedIn: 'root'
})
export class RoleService {
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

  getAllRoles(): Observable<Role[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<Role[]>(`${this.url}/admin/role/getAllRoles`, { headers });
  }

  addRole(role: Partial<Role>): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.post<any>(`${this.url}/admin/role/addRole`, role, { headers });
  }

  updateRole(roleId: number, role: Partial<Role>): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(`${this.url}/admin/role/updateRole/${roleId}`, role, { headers });
  }

  deleteRole(roleId: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.delete<any>(`${this.url}/admin/role/deleteRole/${roleId}`, { headers });
  }
}
