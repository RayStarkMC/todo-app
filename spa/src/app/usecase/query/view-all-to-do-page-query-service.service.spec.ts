import { TestBed } from '@angular/core/testing';

import { ViewAllToDoPageQuery } from './view-all-to-do-page-query.service';

describe('ViewAllToDoPageQuery', () => {
  let service: ViewAllToDoPageQuery;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ViewAllToDoPageQuery);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
