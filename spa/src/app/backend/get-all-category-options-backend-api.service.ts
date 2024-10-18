import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetAllCategoryOptionsBackendAPIService {
  private readonly http = inject(HttpClient)

  run(): Observable<Response> {
    // return this.http.get<Response>("http://localhost:9000/api/query/category-options")
    return of(
      {
        list: [
          {
            id: 1,
            name: "category11",
          },
          {
            id: 2,
            name: "category22",
          },
          {
            id: 3,
            name: "category33",
          }
        ]
      }
    )
  }
}

type Response = {
  list: {
    id: number,
    name: string,
  }[]
}
