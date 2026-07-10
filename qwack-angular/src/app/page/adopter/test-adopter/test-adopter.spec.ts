import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestAdopter } from './test-adopter';

describe('TestAdopter', () => {
  let component: TestAdopter;
  let fixture: ComponentFixture<TestAdopter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TestAdopter],
    }).compileComponents();

    fixture = TestBed.createComponent(TestAdopter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
