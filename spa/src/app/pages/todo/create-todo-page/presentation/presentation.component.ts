import {Component, input, OnInit} from '@angular/core';
import {CommonComponent} from '../../../common/common.component';
import {FormControl, FormGroup, ReactiveFormsModule} from '@angular/forms';

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
export class PresentationComponent implements OnInit {
  readonly state = input.required<State>()
  readonly form = new FormGroup({
    title: new FormControl("", {nonNullable: true}),
    body: new FormControl("", {nonNullable: true}),
    category: new FormControl(1, {nonNullable: true})
  })

  ngOnInit(): void {
    const s = this.state()

    this.form.setValue({
      title: s.title,
      body: s.body,
      category: s.category,
    })
  }
}

export type State = {
  title: string,
  body: string,
  category: number,
  categoryOptions: {
    id: number,
    name: string,
  }[]
}
