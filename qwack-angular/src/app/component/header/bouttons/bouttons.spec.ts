import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bouttons } from './bouttons';

describe('Bouttons', () => {
  let component: Bouttons;
  let fixture: ComponentFixture<Bouttons>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Bouttons],
    }).compileComponents();

    fixture = TestBed.createComponent(Bouttons);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
