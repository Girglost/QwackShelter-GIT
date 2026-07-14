import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilPersonnePage } from './profil-personne-page';

describe('ProfilPersonnePage', () => {
  let component: ProfilPersonnePage;
  let fixture: ComponentFixture<ProfilPersonnePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfilPersonnePage],
    }).compileComponents();

    fixture = TestBed.createComponent(ProfilPersonnePage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
