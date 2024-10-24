import {Component, input, output} from '@angular/core';
import {NgStyle} from '@angular/common';
import {CommonComponent} from '../../../common/common.component';

@Component({
  selector: 'app-presentation',
  standalone: true,
  imports: [
    NgStyle,
    CommonComponent
  ],
  templateUrl: './presentation.component.html',
  styleUrl: './presentation.component.scss'
})
export class PresentationComponent {
  readonly state = input.required<ToDo[]>()

  readonly createButtonClicked = output()
}

export type ToDo = {
  id: number,
  title: string,
  body: string,
  status: "TODO" | "IN_PROGRESS" | "DONE",
  category: {
    name: string,
    color: string,
  },
}
