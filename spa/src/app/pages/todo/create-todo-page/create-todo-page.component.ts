import {Component, inject, OnDestroy, OnInit, signal} from '@angular/core';
import {FormType, PresentationComponent, State} from './presentation/presentation.component';
import {Subject, takeUntil} from 'rxjs';
import {CreateToDoPageQueryService} from '../../../usecase/query/create-to-do-page-query.service';

@Component({
  selector: 'app-create-todo-page',
  standalone: true,
  imports: [
    PresentationComponent
  ],
  templateUrl: './create-todo-page.component.html',
  styleUrl: './create-todo-page.component.scss'
})
export class CreateTodoPageComponent implements OnInit, OnDestroy {
  private readonly query = inject(CreateToDoPageQueryService)
  private readonly unsubscribe = new Subject<void>()

  readonly state = signal<State>({
    categoryOptions: [
      {
        id: 1,
        name: ""
      }
    ],
  })

  ngOnInit(): void {
    this.query.run()
      .pipe(
        takeUntil(this.unsubscribe),
      )
      .subscribe(output =>
        this.state.set(output)
      )
  }

  ngOnDestroy(): void {
    this.unsubscribe.next()
    this.unsubscribe.complete()
  }

  onFormSubmitted(form: FormType) {
    console.log(form.value)
  }
}
