import {Component, OnInit, signal} from '@angular/core';
import {PresentationComponent, State} from './presentation/presentation.component';

@Component({
  selector: 'app-create-todo-page',
  standalone: true,
  imports: [
    PresentationComponent
  ],
  templateUrl: './create-todo-page.component.html',
  styleUrl: './create-todo-page.component.scss'
})
export class CreateTodoPageComponent implements OnInit {
  readonly state = signal<State>({
    title: '',
    body: '',
    category: 1,
    categoryOptions: [
      {
        id: 1,
        name: ""
      }
    ],
  })

  ngOnInit(): void {
    this.state.set({
      title: 'title',
      body: 'bodybody',
      category: 1,
      categoryOptions: [
        {
          id: 1,
          name: "category1"
        },
        {
          id: 2,
          name: "category2"
        }
      ],
    })
  }
}
