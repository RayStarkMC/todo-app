import { TestBed } from '@angular/core/testing';

import { GetAllToDoBackendApi } from './get-all-to-do-backend-api.service';

describe('GetAllToDoBackendApiService', () => {
  let service: GetAllToDoBackendApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAllToDoBackendApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
