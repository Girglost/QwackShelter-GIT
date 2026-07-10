import { TestBed } from '@angular/core/testing';

import { HistoriqueSanteService } from './historique-sante-service';

describe('HistoriqueSanteService', () => {
  let service: HistoriqueSanteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HistoriqueSanteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
