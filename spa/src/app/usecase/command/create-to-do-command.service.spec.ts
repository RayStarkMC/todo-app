import { TestBed } from '@angular/core/testing';

import { CreateToDoCommandService } from './create-to-do-command.service';

describe('CreateToDoCommandService', () => {
  let service: CreateToDoCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateToDoCommandService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
