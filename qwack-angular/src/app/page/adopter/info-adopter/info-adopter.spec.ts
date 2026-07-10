import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoAdopter } from './info-adopter';

describe('InfoAdopter', () => {
  let component: InfoAdopter;
  let fixture: ComponentFixture<InfoAdopter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InfoAdopter],
    }).compileComponents();

    fixture = TestBed.createComponent(InfoAdopter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
