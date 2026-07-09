import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoriqueSantePage } from './historique-sante-page';

describe('HistoriqueSantePage', () => {
  let component: HistoriqueSantePage;
  let fixture: ComponentFixture<HistoriqueSantePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HistoriqueSantePage],
    }).compileComponents();

    fixture = TestBed.createComponent(HistoriqueSantePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
