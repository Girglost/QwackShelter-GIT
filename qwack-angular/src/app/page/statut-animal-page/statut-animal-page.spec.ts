import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StatutAnimalPage } from './statut-animal-page';

describe('StatutAnimalPage', () => {
  let component: StatutAnimalPage;
  let fixture: ComponentFixture<StatutAnimalPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StatutAnimalPage],
    }).compileComponents();

    fixture = TestBed.createComponent(StatutAnimalPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
