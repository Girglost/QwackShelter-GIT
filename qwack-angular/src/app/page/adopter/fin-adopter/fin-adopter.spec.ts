import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinAdopter } from './fin-adopter';

describe('FinAdopter', () => {
  let component: FinAdopter;
  let fixture: ComponentFixture<FinAdopter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FinAdopter],
    }).compileComponents();

    fixture = TestBed.createComponent(FinAdopter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
