import {Component, inject, input, OnDestroy, OnInit, Signal, signal, WritableSignal} from '@angular/core';
import {FormType, PresentationComponent, State} from './presentation/presentation.component';
import {Router} from '@angular/router';
import {Subject, takeUntil} from 'rxjs';
import {EditToDoCommandService} from '../../../usecase/command/edit-to-do-command.service';
import {DeleteToDoCommandService} from '../../../usecase/command/delete-to-do-command.service';
import {EditToDoPageQueryService} from '../../../usecase/query/edit-to-do-page-query.service';

@Component({
  selector: 'app-edit-todo-page',
  standalone: true,
  imports: [
    PresentationComponent
  ],
  templateUrl: './edit-todo-page.component.html',
  styleUrl: './edit-todo-page.component.scss'
})
export class EditTodoPageComponent implements OnInit, OnDestroy {
  private readonly router = inject(Router)
  private readonly query = inject(EditToDoPageQueryService)
  private readonly editService = inject(EditToDoCommandService)
  private readonly deleteService = inject(DeleteToDoCommandService)
  private readonly unsubscribe = new Subject<void>()

  readonly id = input.required<string>()
  readonly state = signal<State>({
    type: "pre_initialized"
  })
  private typedId: number | undefined

  ngOnInit(): void {
    const result = parseInt(this.id())
    if (isNaN(result)) {
      const _ = this.router.navigateByUrl("/")
    }
    this.typedId = result
    this.query
      .run()
      .pipe(
        takeUntil(this.unsubscribe),
      )
      .subscribe(output => {
        this.state.set(
          {
            type: "initialized",
            id: this.typedId!,
            submitFailed: false,
            statusOptions: [
              {id: 1, name: "status1"},
              {id: 2, name: "status2"},
              {id: 3, name: "status3"},
            ],
            ...output,
          }
        )
      })
  }

  ngOnDestroy(): void {
    this.unsubscribe.next()
    this.unsubscribe.complete()
  }

  onFormSubmitted(form: FormType) {
    this.editService
      .run({
        id: this.typedId!,
        title: form.title,
        body: form.body,
        category: form.category,
        status: form.status,
      })
      .pipe(
        takeUntil(this.unsubscribe),
      )
      .subscribe({
        complete: () => this.router.navigateByUrl("/todo"),
        error: err => {
          this.state.update((s) => {
            return {
              ...s,
              submitFailed: true,
            }
          })
        }
      })
  }

  onDeleted() {
    this.deleteService
      .run({
        id: this.typedId!
      })
      .pipe(
        takeUntil(this.unsubscribe),
      )
      .subscribe({
        complete: () => this.router.navigateByUrl("/todo"),
        error: err => {
          this.state.update((s) => {
            return {
              ...s,
              submitFailed: true,
            }
          })
        }
      })
  }
}
