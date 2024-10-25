import { Injectable } from '@angular/core';
import {Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DeleteToDoCommandService {
  //private readonly api = inject(DeleteToDoBackendAPIService)

  run(input: Input): Observable<void> {
    return throwError(() => new Error(""))
    //return EMPTY
    // return this.api.run(input).pipe(
    //   concatMap(() => EMPTY)
    // )
  }
}

export type Input = {
  id: number,
}
