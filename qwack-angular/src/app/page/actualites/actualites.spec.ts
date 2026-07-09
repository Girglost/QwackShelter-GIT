import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule } from '@angular/forms';

import { Actualites } from './actualites';

describe('Actualites', () => {

  let component: Actualites;
  let fixture: ComponentFixture<Actualites>;

  beforeEach(async () => {

    await TestBed.configureTestingModule({

      declarations: [ Actualites ],
      imports: [ FormsModule ]

    })
    .compileComponents();

  });

  beforeEach(() => {

    fixture = TestBed.createComponent(Actualites);

    component = fixture.componentInstance;

    fixture.detectChanges();

  });

  it('should create', () => {

    expect(component).toBeTruthy();

  });

  it('should contain six news', () => {

    expect(component.actualites.length).toBe(6);

  });

});
