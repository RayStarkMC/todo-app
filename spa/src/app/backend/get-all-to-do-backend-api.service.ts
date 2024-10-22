import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class GetAllToDoBackendApi {
  private readonly http = inject(HttpClient)

  run(): Observable<Response> {
    return this.http.get<Response>(`${environment.apiBaseUrl}/api/query/todo`)
  }

}

type Response = {
  list: {
    id: number,
    title: string,
    body: string,
    status: 0 | 1 | 2,
    category: {
      name: string,
      color: string,
    },
  }[]
}

