import { TestBed } from '@angular/core/testing';

import { GetAllCategoryOptionsBackendAPIService } from './get-all-category-options-backend-api.service';

describe('GetAllCategoryOptionsBackendAPIService', () => {
  let service: GetAllCategoryOptionsBackendAPIService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GetAllCategoryOptionsBackendAPIService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
