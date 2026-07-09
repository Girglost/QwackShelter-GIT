import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NosAnimaux } from './nos-animaux';

describe('NosAnimaux', () => {
  let component: NosAnimaux;
  let fixture: ComponentFixture<NosAnimaux>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NosAnimaux],
    }).compileComponents();

    fixture = TestBed.createComponent(NosAnimaux);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
