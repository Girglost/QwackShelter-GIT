import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmplacementPage } from './emplacement-page';

describe('EmplacementPage', () => {
  let component: EmplacementPage;
  let fixture: ComponentFixture<EmplacementPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmplacementPage],
    }).compileComponents();

    fixture = TestBed.createComponent(EmplacementPage);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
