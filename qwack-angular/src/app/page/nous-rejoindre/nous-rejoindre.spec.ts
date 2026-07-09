import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NousRejoindre, atLeastOneChecked, optionalPhoneValidator } from './nous-rejoindre';
import { FormArray, FormBuilder } from '@angular/forms';

describe('NousRejoindre', () => {
  let component: NousRejoindre;
  let fixture: ComponentFixture<NousRejoindre>;

  const fillValid = () => {
    component.benevoleForm.patchValue({
      firstName: 'Alex',
      lastName: 'Martin',
      email: 'alex.martin@example.com',
      phone: '',
      age: 22,
      motivation: 'Je souhaite aider les animaux du refuge chaque week-end.',
      acceptRules: true,
    });
    component.availabilityArray.at(0).setValue(true);
    component.missionsArray.at(0).setValue(true);
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NousRejoindre],
    }).compileComponents();

    fixture = TestBed.createComponent(NousRejoindre);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should be invalid when empty', () => {
    expect(component.benevoleForm.valid).toBe(false);
  });

  it('should be valid once filled correctly', () => {
    fillValid();
    expect(component.benevoleForm.valid).toBe(true);
  });

  describe('required text fields', () => {
    (['firstName', 'lastName', 'email', 'motivation'] as const).forEach((field) => {
      it(`marks "${field}" invalid when left blank`, () => {
        fillValid();
        component.benevoleForm.get(field)?.setValue('');
        expect(component.benevoleForm.get(field)?.valid).toBe(false);
        expect(component.benevoleForm.valid).toBe(false);
      });
    });
  });

  it('rejects a malformed email address', () => {
    fillValid();
    component.benevoleForm.get('email')?.setValue('not-an-email');
    expect(component.benevoleForm.get('email')?.hasError('email')).toBe(true);
  });

  it('rejects an age under 16', () => {
    fillValid();
    component.benevoleForm.get('age')?.setValue(12);
    expect(component.benevoleForm.get('age')?.hasError('min')).toBe(true);
  });

  it('requires the internal rules checkbox to be checked', () => {
    fillValid();
    component.benevoleForm.get('acceptRules')?.setValue(false);
    expect(component.benevoleForm.get('acceptRules')?.hasError('required')).toBe(true);
  });

  describe('optional phone field', () => {
    it('is valid when left empty', () => {
      fillValid();
      component.benevoleForm.get('phone')?.setValue('');
      expect(component.benevoleForm.get('phone')?.valid).toBe(true);
    });

    it('rejects an obviously invalid phone number', () => {
      fillValid();
      component.benevoleForm.get('phone')?.setValue('abc');
      expect(component.benevoleForm.get('phone')?.hasError('invalidPhone')).toBe(true);
    });
  });

  describe('availability / missions checkbox arrays', () => {
    it('is invalid when no availability is checked', () => {
      fillValid();
      component.availabilityArray.controls.forEach((c) => c.setValue(false));
      expect(component.availabilityArray.hasError('atLeastOne')).toBe(true);
      expect(component.benevoleForm.valid).toBe(false);
    });

    it('is invalid when no mission is checked', () => {
      fillValid();
      component.missionsArray.controls.forEach((c) => c.setValue(false));
      expect(component.missionsArray.hasError('atLeastOne')).toBe(true);
      expect(component.benevoleForm.valid).toBe(false);
    });

    it('is valid as soon as one option is checked', () => {
      fillValid();
      expect(component.availabilityArray.valid).toBe(true);
      expect(component.missionsArray.valid).toBe(true);
    });
  });

  describe('atLeastOneChecked (standalone)', () => {
    it('returns an error when every control is false', () => {
      const array = new FormBuilder().array([false, false, false]);
      expect(atLeastOneChecked(array)).toEqual({ atLeastOne: true });
    });

    it('returns null when at least one control is true', () => {
      const array = new FormBuilder().array([false, true, false]);
      expect(atLeastOneChecked(array)).toBeNull();
    });
  });

  describe('optionalPhoneValidator (standalone)', () => {
    it('returns null for empty values', () => {
      expect(optionalPhoneValidator({ value: '' } as any)).toBeNull();
    });

    it('returns an error for invalid non-empty values', () => {
      expect(optionalPhoneValidator({ value: 'call me' } as any)).toEqual({
        invalidPhone: true,
      });
    });
  });

  describe('onSubmit()', () => {
    it('does not submit when the form is invalid', () => {
      const logSpy = spyOn(console, 'log');
      component.onSubmit();

      expect(component.submitted).toBe(false);
      expect(logSpy).not.toHaveBeenCalled();
      expect(component.benevoleForm.get('email')?.touched).toBe(true);
    });

    it('submits, logs the payload and resets the form when valid', () => {
      const logSpy = spyOn(console, 'log');
      fillValid();

      component.onSubmit();

      expect(logSpy).toHaveBeenCalledTimes(1);
      expect(component.submitted).toBe(true);
      expect(component.benevoleForm.get('firstName')?.value).toBe(false);
      expect((component.benevoleForm.get('availability') as FormArray).value).toEqual([
        false,
        false,
        false,
      ]);
    });
  });
});
