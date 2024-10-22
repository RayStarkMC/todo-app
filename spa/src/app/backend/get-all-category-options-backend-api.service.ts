import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GetAllCategoryOptionsBackendAPIService {
  private readonly http = inject(HttpClient)

  run(): Observable<Response> {
    return this.http.get<Response>(`${environment.apiBaseUrl}/api/query/category-option`)
  }
}

type Response = {
  list: {
    id: number,
    name: string,
  }[]
}
