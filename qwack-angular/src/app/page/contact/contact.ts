import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';

/** Loose international-friendly phone pattern: digits, spaces, +, -, () */
const PHONE_PATTERN = /^[0-9+()\-.\s]{6,20}$/;

export interface ContactFormValue {
  subject: string;
  firstName: string;
  lastName: string;
  email: string;
  password: string;
  phone: string;
  message: string;
}

@Component({
  selector: 'app-contact',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './contact.html',
  styleUrls: ['./contact.css'],
})

export class Contact {
    submitted = false;

  readonly contactForm: FormGroup;

  constructor(private readonly fb: FormBuilder) {
    this.contactForm = this.fb.group({
      subject: ['', [Validators.required]],
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(8)]],
      phone: ['', [optionalPhoneValidator]],
      message: ['', [Validators.required, Validators.minLength(5)]],
    });
  }

  /** True when a control has been touched/dirtied and currently fails validation. */
  isInvalid(controlName: keyof ContactFormValue): boolean {
    const control = this.contactForm.get(controlName);
    return !!control && control.invalid && (control.touched || control.dirty);
  }

  onSubmit(): void {
    if (this.contactForm.invalid) {
      this.contactForm.markAllAsTouched();
      this.submitted = false;
      return;
    }

    const value = this.contactForm.value as ContactFormValue;
    this.submitted = true;
    // Hand-off point: wire this up to a real API call (e.g. HttpClient.post).
    console.log('Contact form submitted', value);

    this.contactForm.reset();
    this.submitted = true;
  }
}

/** Phone is optional: empty is valid, but a non-empty value must look like a phone number. */
export function optionalPhoneValidator(control: AbstractControl): ValidationErrors | null {
  const value = control.value;
  if (value === null || value === undefined || value === '') {
    return null;
  }
  return PHONE_PATTERN.test(value) ? null : { invalidPhone: true };

}
