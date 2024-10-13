import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../auth.service';
import { DocumentType } from 'src/app/Model/admin/document-type.model';

@Injectable({
  providedIn: 'root'
})
export class DocumentTypeService {
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

  getAllDocumentTypes(): Observable<DocumentType[]> {
    const headers = this.getAuthHeaders();
    return this.httpClient.get<DocumentType[]>(`${this.url}/admin/documetType/getAllDocumentTypes`, { headers });
  }

  addDocumentType(newDocumentType: DocumentType): Observable<any> {
    console.log(newDocumentType);
    const headers = this.getAuthHeaders();
    return this.httpClient.post<any>(
      `${this.url}/admin/documetType/addDocumentType`,  // Corrected URL
      newDocumentType,
      { headers }
    );
  }
  

  updateDocumentType(documentTypeId: number, documentType: string): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.put<any>(
      `${this.url}/admin/documetType/updateDocumentType/${documentTypeId}?documentType=${documentType}`, 
      {}, 
      { headers }
    );
  }

  deleteDocumentType(documentTypeId: number): Observable<any> {
    const headers = this.getAuthHeaders();
    return this.httpClient.delete<any>(`${this.url}/admin/documetType/deleteDocumentType/${documentTypeId}`, { headers });
  }
}
