import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetAllToDoBackendApi {
  private readonly http = inject(HttpClient)

  run(request: Request): Observable<Response> {
    return this.http.get<Response>("http://localhost:9000/api/todo")
  }

}

type Request = {}
type Response = {
  list: {
    id: number,
    title: string,
    body: string,
    status: "TODO" | "IN_PROGRESS" | "DONE"
    category: {
      name: string,
      color: string,
    },
  }[]
}

