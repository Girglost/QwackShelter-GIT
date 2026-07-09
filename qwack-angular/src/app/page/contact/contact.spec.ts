import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Contact, ContactFormValue, optionalPhoneValidator } from './contact';

describe('Contact', () => {
  let component: Contact;
  let fixture: ComponentFixture<Contact>;

  const validValue: ContactFormValue = {
    subject: 'Adoption',
    firstName: 'Marie',
    lastName: 'Dupont',
    email: 'marie.dupont@example.com',
    password: 'sup3rSecret',
    phone: '',
    message: 'Bonjour, je suis intéressé par une adoption.',
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Contact],
    }).compileComponents();

    fixture = TestBed.createComponent(Contact);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).to.exist;
  });

  it('should be invalid when empty', () => {
    expect(component.contactForm.valid).to.equal(false);
  });

  it('should be valid once all required fields are filled correctly (phone omitted)', () => {
    component.contactForm.setValue(validValue);
    expect(component.contactForm.valid).to.equal(true);
  });

  describe('required fields', () => {
    (['subject', 'firstName', 'lastName', 'email', 'password', 'message'] as const).forEach(
      (field) => {
        it(`marks "${field}" invalid when left blank`, () => {
          component.contactForm.setValue(validValue);
          component.contactForm.get(field)?.setValue('');
          expect(component.contactForm.get(field)?.valid).toBeFalse();
          expect(component.contactForm.valid).toBeFalse();
        });
      }
    );
  });

  it('should reject a malformed email address', () => {
    component.contactForm.setValue({ ...validValue, email: 'not-an-email' });
    expect(component.contactForm.get('email')?.hasError('email')).to.equal(true);
  });

  it('should require a password of at least 8 characters', () => {
    component.contactForm.setValue({ ...validValue, password: 'short' });
    expect(component.contactForm.get('password')?.hasError('minlength')).to.equal(true);
  });

  describe('optional phone field', () => {
    it('is valid when left empty', () => {
      component.contactForm.setValue({ ...validValue, phone: '' });
      expect(component.contactForm.get('phone')?.valid).to.equal(true);
    });

    it('accepts a well-formed phone number', () => {
      component.contactForm.setValue({ ...validValue, phone: '+33 2 34 56 78 90' });
      expect(component.contactForm.get('phone')?.valid).to.equal(true);
    });

    it('rejects an obviously invalid phone number', () => {
      component.contactForm.setValue({ ...validValue, phone: 'abc' });
      expect(component.contactForm.get('phone')?.hasError('invalidPhone')).to.equal(true);
    });
  });

  describe('optionalPhoneValidator (standalone)', () => {
    it('returns null for empty/undefined/null values', () => {
      expect(optionalPhoneValidator({ value: '' } as any)).to.be.null;
      expect(optionalPhoneValidator({ value: null } as any)).to.be.null;
      expect(optionalPhoneValidator({ value: undefined } as any)).to.be.null;
    });

    it('returns an error object for invalid non-empty values', () => {
      expect(optionalPhoneValidator({ value: 'call me maybe' } as any)).to.deep.equal({
        invalidPhone: true,
      });
    });
  });

  describe('isInvalid()', () => {
    it('is false for an untouched, unedited control even if empty', () => {
      expect(component.isInvalid('subject')).to.equal(false);
    });

    it('is true once a required control has been touched and is still empty', () => {
      component.contactForm.get('subject')?.markAsTouched();
      expect(component.isInvalid('subject')).to.equal(true);
    });

    it('is false once a previously invalid control becomes valid', () => {
      const control = component.contactForm.get('subject');
      control?.markAsTouched();
      control?.setValue('Adoption');
      expect(component.isInvalid('subject')).to.equal(false);
    });
  });

  describe('onSubmit()', () => {
    it('does not submit and touches all controls when the form is invalid', () => {
      const logSpy = spyOn(console, 'log');
      component.onSubmit();

      expect(component.submitted).to.equal(false);
      expect(logSpy.calls.count()).to.equal(0);
      expect(component.contactForm.get('email')?.touched).to.equal(true);
    });

    it('submits and resets the form when all values are valid', () => {
      const logSpy = spyOn(console, 'log');
      component.contactForm.setValue(validValue);

      component.onSubmit();

      expect(logSpy.calls.count()).to.equal(1);
      expect(component.submitted).to.equal(true);
      expect(component.contactForm.get('subject')?.value).to.equal('');
    });
  });
});
function spyOn<T extends object, K extends keyof T>(object: T, method: K) {
  let callCount = 0;

  const spy = {
    calls: {
      count: () => callCount,
    },
  } as const;

  (object as any)[method] = () => {
    callCount += 1;
  };

  return spy;
}

