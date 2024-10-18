import {inject, Injectable} from '@angular/core';
import {map, Observable} from 'rxjs';
import {GetAllCategoryOptionsBackendAPIService} from '../../backend/get-all-category-options-backend-api.service';

@Injectable({
  providedIn: 'root'
})
export class CreateToDoPageQueryService {
  private readonly api = inject(GetAllCategoryOptionsBackendAPIService)

  run(): Observable<Output> {
    return this.api.run()
      .pipe(
        map(res => {
          return {
            categoryOptions: res.list
          }
        })
      )
  }
}

type Output = {
  categoryOptions: {
    id: number,
    name: string,
  }[],
}
