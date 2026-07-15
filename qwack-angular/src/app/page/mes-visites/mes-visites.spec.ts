import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesVisites } from './mes-visites';

describe('MesVisites', () => {
  let component: MesVisites;
  let fixture: ComponentFixture<MesVisites>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MesVisites],
    }).compileComponents();

    fixture = TestBed.createComponent(MesVisites);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
