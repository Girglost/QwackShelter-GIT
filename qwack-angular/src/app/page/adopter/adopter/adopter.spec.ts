import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Adopter } from '../adopter';

describe('Adopter', () => {
  let component: Adopter;
  let fixture: ComponentFixture<Adopter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Adopter],
    }).compileComponents();

    fixture = TestBed.createComponent(Adopter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
