import { TestBed } from '@angular/core/testing';

import { QuackShelterService } from './quack-shelter-service';

describe('QuackShelterService', () => {
  let service: QuackShelterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(QuackShelterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
