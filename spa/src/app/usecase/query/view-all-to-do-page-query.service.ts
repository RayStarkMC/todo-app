import {inject, Injectable} from '@angular/core';
import {map, Observable} from 'rxjs';
import {GetAllToDoBackendApi} from '../../backend/get-all-to-do-backend-api.service';

@Injectable({
  providedIn: 'root'
})
export class ViewAllToDoPageQuery {
  api = inject(GetAllToDoBackendApi)

  run(): Observable<Output> {
    return this.api
      .run()
      .pipe(
        map((response, _) => ({
          list: response.list.map(todo => ({
            id: todo.id,
            title: todo.title,
            body: todo.body,
            status: (() => {
              switch (todo.status) {
                case 0:
                  return "TODO";
                case 1:
                  return "IN_PROGRESS"
                case 2:
                  return "DONE"
              }
            })(),
            category: todo.category
          }))
        }))
      )
  }
}

type Output = {
  list: {
    id: number,
    title: string,
    body: string,
    status: "TODO" | "IN_PROGRESS" | "DONE",
    category: {
      name: string,
      color: string,
    },
  }[]
}
