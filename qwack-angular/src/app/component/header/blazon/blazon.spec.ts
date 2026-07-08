import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Blazon } from './blazon';

describe('Blazon', () => {
  let component: Blazon;
  let fixture: ComponentFixture<Blazon>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Blazon],
    }).compileComponents();

    fixture = TestBed.createComponent(Blazon);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
