import {Component, computed, inject, input, OnInit, signal} from '@angular/core';
import {PresentationComponent, State} from './presentation/presentation.component';
import {Router} from '@angular/router';

@Component({
  selector: 'app-edit-todo-page',
  standalone: true,
    imports: [
        PresentationComponent
    ],
  templateUrl: './edit-todo-page.component.html',
  styleUrl: './edit-todo-page.component.scss'
})
export class EditTodoPageComponent implements OnInit {
  private readonly router = inject(Router)
  readonly id = input.required<string>()
  readonly state = signal<State>({
    type: "pre_initialized"
  })

  ngOnInit(): void {
    const result = parseInt(this.id())
    if (isNaN(result)) {
      const _ = this.router.navigateByUrl("/")
    }
    this.state.set({
      type: "initialized",
      id: result,
    })
  }
}
