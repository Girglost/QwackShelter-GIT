import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoRefugeFooter } from './info-refuge-footer';

describe('InfoRefugeFooter', () => {
  let component: InfoRefugeFooter;
  let fixture: ComponentFixture<InfoRefugeFooter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InfoRefugeFooter],
    }).compileComponents();

    fixture = TestBed.createComponent(InfoRefugeFooter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
