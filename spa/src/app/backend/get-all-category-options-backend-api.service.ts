import {inject, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GetAllCategoryOptionsBackendAPIService {
  private readonly http = inject(HttpClient)

  run(): Observable<Response> {
    return this.http.get<Response>("http://localhost:9000/api/query/category-option")
  }
}

type Response = {
  list: {
    id: number,
    name: string,
  }[]
}
