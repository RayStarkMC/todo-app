import {Component, input} from '@angular/core';
import {NgStyle} from '@angular/common';

@Component({
  selector: 'app-presentation',
  standalone: true,
  imports: [
    NgStyle
  ],
  templateUrl: './presentation.component.html',
  styleUrl: './presentation.component.scss'
})
export class PresentationComponent {
  readonly state = input.required<ToDo[]>()
  protected readonly console = console;
}

export type ToDo = {
  id: number,
  title: string,
  body: string,
  status: "TODO" | "IN_PROGRESS" | "DONE",
  category: {
    id: number,
    name: string,
    color: string
  },
}
