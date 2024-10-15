import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetAllToDoBackendApi {
  private readonly http = inject(HttpClient)

  run(): Observable<Response> {
    return this.http.get<Response>("http://localhost:9000/api/todo")
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

