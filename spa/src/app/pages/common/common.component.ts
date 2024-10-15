import {Component, inject, input} from '@angular/core';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-common',
  standalone: true,
  imports: [
    RouterLink
  ],
  templateUrl: './common.component.html',
  styleUrl: './common.component.scss'
})
export class CommonComponent {
  readonly router = inject(Router)
  readonly title = input.required<string>()
}
