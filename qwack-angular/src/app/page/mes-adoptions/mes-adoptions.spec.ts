import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MesAdoptions } from './mes-adoptions';

describe('MesAdoptions', () => {
  let component: MesAdoptions;
  let fixture: ComponentFixture<MesAdoptions>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MesAdoptions],
    }).compileComponents();

    fixture = TestBed.createComponent(MesAdoptions);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
