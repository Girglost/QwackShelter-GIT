import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DebutAdopter } from './debut-adopter';

describe('DebutAdopter', () => {
  let component: DebutAdopter;
  let fixture: ComponentFixture<DebutAdopter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DebutAdopter],
    }).compileComponents();

    fixture = TestBed.createComponent(DebutAdopter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
