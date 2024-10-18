import {inject, Injectable} from '@angular/core';
import {CreateToDoBackendAPIService} from '../../backend/create-to-do-backend-api.service';
import {concatMap, EMPTY, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CreateToDoCommandService {
  private readonly api = inject(CreateToDoBackendAPIService)

  run(input: Input): Observable<void> {
    return this.api.run(input).pipe(
      concatMap(() => EMPTY)
    )
  }
}

type Input = {
  title: string,
  body: string,
  category: number,
}
