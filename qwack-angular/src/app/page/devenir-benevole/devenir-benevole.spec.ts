import { ComponentFixture, TestBed } from '@angular/core/testing';
import { DevenirBenevole } from './devenir-benevole';

describe('DevenirBenevole', () => {

  let component: DevenirBenevole;
  let fixture: ComponentFixture<DevenirBenevole>;

  beforeEach(async () => {

    await TestBed.configureTestingModule({
      imports: [DevenirBenevole]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DevenirBenevole);

    component = fixture.componentInstance;

    fixture.detectChanges();

  });


  it('should create', () => {

    expect(component).toBeTruthy();

  });


  it('should have an invalid form initially', () => {

    expect(component.benevoleForm.invalid).toEqual(true);

  });


  it('should require a motivation message', () => {

    const motivation = component.benevoleForm.get('motivation');

    motivation?.setValue('');

    expect(motivation?.invalid).toEqual(true);

  });


});
