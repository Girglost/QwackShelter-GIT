import { TestBed } from '@angular/core/testing';

import { StatutAnimalService } from './statut-animal-service';

describe('StatutAnimalService', () => {
  let service: StatutAnimalService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StatutAnimalService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
