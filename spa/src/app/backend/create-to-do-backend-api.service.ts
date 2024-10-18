import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateToDoBackendAPIService {
  private readonly http = inject(HttpClient)

  run(request: Request): Observable<Response> {
    return this.http.post<Response>(
      "http://localhost:9000/api/command/todo",
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
