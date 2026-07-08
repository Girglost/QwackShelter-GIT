import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewsletterFooter } from './newsletter-footer';

describe('NewsletterFooter', () => {
  let component: NewsletterFooter;
  let fixture: ComponentFixture<NewsletterFooter>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NewsletterFooter],
    }).compileComponents();

    fixture = TestBed.createComponent(NewsletterFooter);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
