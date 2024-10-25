import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTodoPageComponent } from './edit-todo-page.component';

describe('EditTodoPageComponent', () => {
  let component: EditTodoPageComponent;
  let fixture: ComponentFixture<EditTodoPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditTodoPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditTodoPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
