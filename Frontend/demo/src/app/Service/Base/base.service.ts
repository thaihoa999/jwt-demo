import { HttpHeaders } from '@angular/common/http';

export class BaseService {
  constructor() { }

  protected getHttpHeaders(token: any): HttpHeaders {
    return new HttpHeaders()
      .set('Access-Control-Allow-Headers', 'Content-Type')
      .set('Content-Type', 'application/json')
      .set('Access-Control-Allow-Methods', '*')
      .set('Access-Control-Allow-Origin', '*')
      .set('Authorization', `Bearer ${token}`);
  }
}
