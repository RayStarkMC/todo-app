import { Injectable } from '@angular/core';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateToDoPageQueryService {
  run(): Observable<Output> {
    return of(
      {
        categoryOptions: [
          {
            id: 1,
            name: "category1",
          },
          {
            id: 2,
            name: "category2",
          },
          {
            id: 3,
            name: "category3",
          }
        ]
      }
    )
  }
}

type Output = {
  categoryOptions: {
    id: number,
    name: string,
  }[],
}
