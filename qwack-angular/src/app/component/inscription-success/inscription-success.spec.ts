import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InscriptionSuccess } from './inscription-success';

describe('InscriptionSuccess', () => {
  let component: InscriptionSuccess;
  let fixture: ComponentFixture<InscriptionSuccess>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [InscriptionSuccess],
    }).compileComponents();

    fixture = TestBed.createComponent(InscriptionSuccess);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
