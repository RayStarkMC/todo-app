import { TestBed } from '@angular/core/testing';

import { EditToDoPageQueryService } from './edit-to-do-page-query.service';

describe('EditToDoPageQueryService', () => {
  let service: EditToDoPageQueryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditToDoPageQueryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
