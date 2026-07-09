import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NosMissions } from './nos-missions';

describe('NosMissions', () => {

  let component: NosMissions;
  let fixture: ComponentFixture<NosMissions>;


  beforeEach(async () => {

    await TestBed.configureTestingModule({

      imports: [NosMissions]

    })
    .compileComponents();


    fixture = TestBed.createComponent(NosMissions);

    component = fixture.componentInstance;

    fixture.detectChanges();

  });



  it('should create', () => {

    expect(component).toBeTruthy();

  });



  it('should contain missions', () => {

    expect(component.missions.length).toBeGreaterThan(0);

  });



});

