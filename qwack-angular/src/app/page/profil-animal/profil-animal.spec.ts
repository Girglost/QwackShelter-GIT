import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilAnimal } from './profil-animal';

describe('ProfilAnimal', () => {
  let component: ProfilAnimal;
  let fixture: ComponentFixture<ProfilAnimal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProfilAnimal],
    }).compileComponents();

    fixture = TestBed.createComponent(ProfilAnimal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
