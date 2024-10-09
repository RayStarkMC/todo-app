import {Component, OnInit, signal} from '@angular/core';
import {ItemComponent, ToDo} from './item/item.component';
import {ListComponent} from './list/list.component';
import {Observable, of} from 'rxjs';

@Component({
  selector: 'app-view-all-page-todos',
  standalone: true,
  imports: [
    ItemComponent,
    ListComponent
  ],
  templateUrl: './view-all-todos-page.component.html',
  styleUrl: './view-all-todos-page.component.scss'
})
export class ViewAllTodosPageComponent implements OnInit {
  readonly state = signal<ToDo[]>([])
  
  ngOnInit(): void {
    mock.subscribe(this.state.set)
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

