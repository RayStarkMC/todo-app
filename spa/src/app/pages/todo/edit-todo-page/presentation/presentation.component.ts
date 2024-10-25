import {Component, input, OnInit, output} from '@angular/core';
import {CommonComponent} from '../../../common/common.component';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-presentation',
  standalone: true,
  imports: [
    CommonComponent,
    FormsModule,
    ReactiveFormsModule
  ],
  templateUrl: './presentation.component.html',
  styleUrl: './presentation.component.scss'
})
export class PresentationComponent implements OnInit {
  readonly state = input.required<State>()
  readonly form = new FormGroup({
    title: new FormControl("", {
      nonNullable: true,
      validators: Validators.required
    }),
    body: new FormControl("", {nonNullable: true}),
    category: new FormControl(1, {nonNullable: true}),
    status: new FormControl(1, {nonNullable: true}),
  })
  readonly formSubmitted = output<FormType>()
  readonly deleted = output()

  ngOnInit(): void {
    const s = this.state()
    if (s.type === "initialized") {
      this.form.setValue({
        title: s.title,
        body: s.body,
        status: s.status,
        category: s.category,
      })
    }
  }

  onSubmit() {
    this.formSubmitted.emit(this.form.getRawValue())
  }

  onDelete() {
    this.deleted.emit()
  }
}

export type State =
  {
    type: "pre_initialized"
  } |
  {
    type: "initialized"
    id: number,
    title: string,
    body: string,
    category: number,
    status: number,
    categoryOptions: {
      id: number,
      name: string,
    }[],
    statusOptions: {
      id: number,
      name: string,
    }[],
    submitFailed: boolean,
  }

export type FormType = ReturnType<
  PresentationComponent["form"]["getRawValue"]
>
