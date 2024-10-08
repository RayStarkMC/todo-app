import { Component } from '@angular/core';
import {ItemComponent, ToDo} from './item/item.component';
import {ListComponent} from './list/list.component';

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
export class ViewAllTodosPageComponent {
  readonly mockToDoList: ToDo[] = [
    {
      id: 1,
      title: 'title1',
      body: 'body1',
      status: 'TODO',
      category: {
        id: 1,
        name: 'category1'
      }
    },
    {
      id: 2,
      title: 'title2',
      body: 'body2',
      status: 'IN_PROGRESS',
      category: {
        id: 1,
        name: 'category2'
      }
    },
    {
      id: 3,
      title: 'title3',
      body: 'body3',
      status: 'DONE',
      category: {
        id: 1,
        name: 'category3'
      }
    }
  ]
}
