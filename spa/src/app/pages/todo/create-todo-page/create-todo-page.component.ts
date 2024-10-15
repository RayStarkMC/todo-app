import {Component, signal} from '@angular/core';
import {PresentationComponent} from './presentation/presentation.component';

@Component({
  selector: 'app-create-todo-page',
  standalone: true,
  imports: [
    PresentationComponent
  ],
  templateUrl: './create-todo-page.component.html',
  styleUrl: './create-todo-page.component.scss'
})
export class CreateTodoPageComponent {
  readonly state = signal<number>(0)
}
