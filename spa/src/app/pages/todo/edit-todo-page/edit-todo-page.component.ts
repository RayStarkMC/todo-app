import {Component, inject, input, OnDestroy, OnInit, signal} from '@angular/core';
import {FormType, PresentationComponent, State} from './presentation/presentation.component';
import {Router} from '@angular/router';
import {Subject, takeUntil} from 'rxjs';

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
  private readonly unsubscribe = new Subject<void>()

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
      title: "initialTitle",
      body: "initialBody",
      category: 1,
      status: 1,
      categoryOptions: [
        {id: 1, name: "category1"},
        {id: 2, name: "category2"},
        {id: 3, name: "category3"},
      ],
      statusOptions: [
        {id: 1, name: "status1"},
        {id: 2, name: "status2"},
        {id: 3, name: "status3"},
      ],
      submitFailed: false,
    })
  }

  ngOnDestroy(): void {
    this.unsubscribe.next()
    this.unsubscribe.complete()
  }

  onFormSubmitted(form: FormType) {
    console.log(form)

    // this.service
    //   .run({
    //     title: form.title,
    //     body: form.body,
    //     category: form.category,
    //     status: form.status,
    //   })
    //   .pipe(
    //     takeUntil(this.unsubscribe),
    //   )
    //   .subscribe({
    //     complete: () => this.router.navigateByUrl("/todo"),
    //     error: err => {
    //       this.state.update((s) => {
    //         return {
    //           submitFailed: true,
    //           categoryOptions: s.categoryOptions
    //         }
    //       })
    //     }
    //   })
  }

  onDeleted() {
    console.log("delete!!!!")
  }
}
