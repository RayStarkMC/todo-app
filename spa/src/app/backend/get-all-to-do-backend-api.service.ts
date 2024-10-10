import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetAllToDoBackendApi {
  private http = inject(HttpClient)

  run(request: Request): Observable<Response> {
    return mock
  }
}

type Request = {}
type Response = {
  id: number,
  title: string,
  body: string,
  status: "TODO" | "IN_PROGRESS" | "DONE"
  category: {
    id: number,
    name: string,
    color: string,
  },
}[]

const mock: Observable<Response> = of(
  [
    {
      id: 1,
      title: 'title1',
      body: 'body1',
      status: 'TODO',
      category: {
        id: 1,
        name: 'category1',
        color: '#ffe4b5'
      }
    },
    {
      id: 2,
      title: 'title2',
      body: 'body2',
      status: 'IN_PROGRESS',
      category: {
        id: 1,
        name: 'category2',
        color: '#00ffff',
      }
    },
    {
      id: 3,
      title: 'title3',
      body: 'body3',
      status: 'DONE',
      category: {
        id: 1,
        name: 'category3',
        color: '#7fffd4'
      }
    }
  ]
)

