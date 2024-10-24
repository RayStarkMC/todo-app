import { TestBed } from '@angular/core/testing';

import { CreateToDoPageQueryService } from './create-to-do-page-query.service';

describe('CreateToDoPageQueryService', () => {
  let service: CreateToDoPageQueryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateToDoPageQueryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
