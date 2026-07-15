import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Events } from './events';

describe('Events', () => {
  let component: Events;
  let fixture: ComponentFixture<Events>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Events],
    }).compileComponents();

    fixture = TestBed.createComponent(Events);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have a non-empty list of upcoming events', () => {
    expect(component.evenementsAVenir.length).toBeGreaterThan(0);
  });

  it('should render each upcoming event title and date in the DOM', () => {
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    const items = compiled.querySelectorAll('.item-a-venir');

    expect(items.length).toBe(component.evenementsAVenir.length);

    component.evenementsAVenir.forEach((evenement, index) => {
      const item = items[index] as HTMLElement;
      expect(item.textContent).toContain(evenement.date);
      expect(item.textContent).toContain(evenement.titre);
    });
  });

});
