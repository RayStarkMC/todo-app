import {Component, input} from '@angular/core';
import {ItemComponent, ToDo} from '../item/item.component';

@Component({
  selector: 'app-list',
  standalone: true,
  imports: [
    ItemComponent
  ],
  templateUrl: './list.component.html',
  styleUrl: './list.component.scss'
})
export class ListComponent {
  readonly state = input.required<ToDo[]>()
}
