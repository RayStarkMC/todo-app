import {Component, input} from '@angular/core';
import {NgStyle} from '@angular/common';

@Component({
  selector: 'app-item',
  standalone: true,
  imports: [
    NgStyle
  ],
  templateUrl: './item.component.html',
  styleUrl: './item.component.scss'
})
export class ItemComponent {
  readonly state = input.required<ToDo>()
}

export type ToDo = Readonly<{
  id: number,
  title: string,
  body: string,
  status: "TODO" | "IN_PROGRESS" | "DONE",
  category: Readonly<{
    id: number,
    name: string,
    color: string
  }>,
}>
