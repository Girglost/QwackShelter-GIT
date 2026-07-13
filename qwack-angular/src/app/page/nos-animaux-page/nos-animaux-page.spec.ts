import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NosAnimauxPage } from './nos-animaux-page';

describe('NosAnimauxPage', () => {
  let component: NosAnimauxPage;
  let fixture: ComponentFixture<NosAnimauxPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NosAnimauxPage],
    }).compileComponents();

    fixture = TestBed.createComponent(NosAnimauxPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
