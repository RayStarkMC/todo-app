import {inject, Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {ToDo} from '../../pages/todo/item/item.component';
import {GetAllToDoBackendApi} from '../../backend/get-all-to-do-backend-api.service';

@Injectable({
  providedIn: 'root'
})
export class ViewAllToDoPageQuery {
  api = inject(GetAllToDoBackendApi)

  run(input: Input): Observable<Output> {
    const request = input
    const response = this.api.run(request)
    return response
  }
}

type Input = {}
type Output = ToDo[]
