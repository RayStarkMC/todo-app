import {Component, input, output} from '@angular/core';
import {CommonComponent} from '../../../common/common.component';
import {FormControl, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';

@Component({
  selector: 'app-presentation',
  standalone: true,
  imports: [
    CommonComponent,
    ReactiveFormsModule,
  ],
  templateUrl: './presentation.component.html',
  styleUrl: './presentation.component.scss'
})
export class PresentationComponent {
  readonly state = input.required<State>()
  readonly form = new FormGroup({
    title: new FormControl("", {
      nonNullable: true,
      validators: Validators.required
    }),
    body: new FormControl("", {nonNullable: true}),
    category: new FormControl(1, {nonNullable: true})
  })
  readonly formSubmitted = output<FormType>()

  onSubmit() {
    this.formSubmitted.emit(this.form)
  }
}

export type State = {
  submitFailed: boolean,
  categoryOptions: {
    id: number,
    name: string,
  }[]
}

export type FormType = PresentationComponent["form"]
