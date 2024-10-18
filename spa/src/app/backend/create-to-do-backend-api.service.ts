import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateToDoBackendAPIService {
  private readonly http = inject(HttpClient)

  run(request: Request): Observable<Response> {
    console.log(request)
    return of({})
    // return this.http.post<Response>(
    //   "http://localhost:9000/api/query/category-option",
    //   request,
    // )
  }
}
type Request = {
  title: string,
  body: string,
  category: number,
}
type Response = {}
