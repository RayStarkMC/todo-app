import {Component, inject, OnDestroy, OnInit, signal} from '@angular/core';
import {ItemComponent, ToDo} from './item/item.component';
import {ListComponent} from './list/list.component';
import {Subject, takeUntil} from 'rxjs';
import {ViewAllToDoPageQuery} from '../../usecase/query/view-all-to-do-page-query.service';

@Component({
  selector: 'app-view-all-page-todos',
  standalone: true,
  imports: [
    ItemComponent,
    ListComponent
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
      .subscribe(this.state.set)
  }


  ngOnDestroy(): void {
    this.unsubscribe.next()
    this.unsubscribe.complete()
  }
}
