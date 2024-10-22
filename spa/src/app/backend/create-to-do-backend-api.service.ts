import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class CreateToDoBackendAPIService {
  private readonly http = inject(HttpClient)

  run(request: Request): Observable<Response> {
    return this.http.post<Response>(
      `${environment.apiBaseUrl}/api/command/todo`,
      request,
    )
  }
}
type Request = {
  title: string,
  body: string,
  category: number,
}
type Response = {}
