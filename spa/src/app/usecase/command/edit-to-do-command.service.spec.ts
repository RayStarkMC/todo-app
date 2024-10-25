import { TestBed } from '@angular/core/testing';

import { EditToDoCommandService } from './edit-to-do-command.service';

describe('EditToDoCommandService', () => {
  let service: EditToDoCommandService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EditToDoCommandService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
