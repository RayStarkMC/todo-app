import {Component, inject, OnDestroy, OnInit, signal} from '@angular/core';
import {Subject, takeUntil} from 'rxjs';
import {ViewAllToDoPageQuery} from '../../usecase/query/view-all-to-do-page-query.service';
import {PresentationComponent, ToDo} from './presentation/presentation.component';
import {CommonComponent} from './common/common.component';

@Component({
  selector: 'app-view-all-page-todos',
  standalone: true,
  imports: [
    PresentationComponent,
    CommonComponent,
  ],
  templateUrl: './view-all-todos-page.component.html',
  styleUrl: './view-all-todos-page.component.scss'
})
export class ViewAllTodosPageComponent implements OnInit, OnDestroy {
  private readonly query = inject(ViewAllToDoPageQuery)
  private readonly unsubscribe = new Subject<void>()

  readonly state = signal<ToDo[]>([])

  ngOnInit(): void {
    const input = {}
    this.query.run(input)
      .pipe(
        takeUntil(this.unsubscribe),
      )
      .subscribe(output =>
        this.state.set(output.list)
      )
  }


  ngOnDestroy(): void {
    this.unsubscribe.next()
    this.unsubscribe.complete()
  }
}
