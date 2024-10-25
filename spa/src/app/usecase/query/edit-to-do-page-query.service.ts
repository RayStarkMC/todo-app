import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EditToDoPageQueryService {
  // private readonly api = inject(GetAllCategoryOptionsBackendAPIService)

  run(): Observable<Output> {
    return of(
      {
        title: "initialTitle",
        body: "initialBody",
        category: 1,
        status: 1,
        categoryOptions: [
          {id: 1, name: "category1"},
          {id: 2, name: "category2"},
          {id: 3, name: "category3"},
        ],
        submitFailed: false,
      }
    )

    // return this.api.run()
    //   .pipe(
    //     map(res => {
    //       return {
    //         categoryOptions: res.list
    //       }
    //     })
    //   )
  }
}

export type Output = {
  title: string,
  body: string,
  category: number,
  status: number,
  categoryOptions: {
    id: number,
    name: string,
  }[],　
}
