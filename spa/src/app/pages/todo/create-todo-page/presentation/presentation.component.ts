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
  readonly state = input.required<number>()

  readonly form = new FormGroup({
    titleFC: new FormControl<string | null>(null),
    bodyFC: new FormControl<string | null>(null),
  })

  ngOnInit(): void {
    this.form.setValue({
      titleFC: "1",
      bodyFC: "2",
    })
  }
}

export type form = {
  id: number,
  name: string,
}
