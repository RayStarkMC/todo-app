import {Injectable} from '@angular/core';
import {EMPTY, Observable, throwError} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EditToDoCommandService {
  //private readonly api = inject(EditToDoBackendAPIService)

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
  title: string,
  body: string,
  category: number,
  status: number,
}
