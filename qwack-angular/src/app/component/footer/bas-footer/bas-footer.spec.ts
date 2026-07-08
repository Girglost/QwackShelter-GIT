import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BasFooter } from './bas-footer';

describe('BasFooter', () => {
  let component: BasFooter;
  let fixture: ComponentFixture<BasFooter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BasFooter],
    }).compileComponents();

    fixture = TestBed.createComponent(BasFooter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
