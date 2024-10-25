import { TestBed } from '@angular/core/testing';

import { DeleteToDoCommandService } from './delete-to-do-command.service';

describe('DeleteToDoCommandService', () => {
  let service: DeleteToDoCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DeleteToDoCommandService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
