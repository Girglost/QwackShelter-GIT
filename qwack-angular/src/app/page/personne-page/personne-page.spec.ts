import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PersonnePage } from './personne-page';

describe('PersonnePage', () => {
  let component: PersonnePage;
  let fixture: ComponentFixture<PersonnePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PersonnePage],
    }).compileComponents();

    fixture = TestBed.createComponent(PersonnePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
