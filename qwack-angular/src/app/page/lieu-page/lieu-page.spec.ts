import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LieuPage } from './lieu-page';

describe('LieuPage', () => {
  let component: LieuPage;
  let fixture: ComponentFixture<LieuPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LieuPage],
    }).compileComponents();

    fixture = TestBed.createComponent(LieuPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
