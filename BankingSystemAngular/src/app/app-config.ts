import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ConfigService {
  private readonly baseUrl = 'http://localhost:8085';
  private readonly jwtToken =
    'eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX0FETUlOIl0sInN1YiI6InJhaHVsMDciLCJpYXQiOjE3MjYxNDI2NjUsImV4cCI6MTcyNjIyOTA2NX0.ZCo_DEHAifCD2-KlYz83608pzfjORgiCfUeYcgMEBoE';

  getBaseUrl(): string {
    return this.baseUrl;
  }

  getJwtToken(): string {
    return this.jwtToken;
  }
}
