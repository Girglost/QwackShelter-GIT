import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SwipeCompagnon } from './swipe-compagnon';

describe('SwipeCompagnon', () => {
  let component: SwipeCompagnon;
  let fixture: ComponentFixture<SwipeCompagnon>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SwipeCompagnon],
    }).compileComponents();

    fixture = TestBed.createComponent(SwipeCompagnon);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
