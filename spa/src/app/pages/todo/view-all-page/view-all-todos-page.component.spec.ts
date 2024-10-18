import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewAllTodosPageComponent } from './view-all-todos-page.component';

describe('ViewAllTodosComponent', () => {
  let component: ViewAllTodosPageComponent;
  let fixture: ComponentFixture<ViewAllTodosPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewAllTodosPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewAllTodosPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
