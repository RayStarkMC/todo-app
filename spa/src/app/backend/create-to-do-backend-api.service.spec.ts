import { TestBed } from '@angular/core/testing';

import { CreateToDoBackendAPIService } from './create-to-do-backend-api.service';

describe('CreateToDoBackendAPIService', () => {
  let service: CreateToDoBackendAPIService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateToDoBackendAPIService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
