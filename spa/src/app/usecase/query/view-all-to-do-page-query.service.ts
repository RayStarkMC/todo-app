import {Injectable} from '@angular/core';
import {Observable, of} from 'rxjs';
import {ToDo} from '../../pages/todo/item/item.component';

@Injectable({
  providedIn: 'root'
})
export class ViewAllToDoPageQuery {
  run(): Observable<ToDo[]> {
    return mock
  }
}

const mock: Observable<ToDo[]> = of(
  [
    {
      id: 1,
      title: 'title1',
      body: 'body1',
      status: 'TODO',
      category: {
        id: 1,
        name: 'category1',
        color: '#ffe4b5'
      }
    },
    {
      id: 2,
      title: 'title2',
      body: 'body2',
      status: 'IN_PROGRESS',
      category: {
        id: 1,
        name: 'category2',
        color: '#00ffff',
      }
    },
    {
      id: 3,
      title: 'title3',
      body: 'body3',
      status: 'DONE',
      category: {
        id: 1,
        name: 'category3',
        color: '#7fffd4'
      }
    }
  ]
)
