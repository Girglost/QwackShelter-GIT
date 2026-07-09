import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import {
  AbstractControl,
  FormArray,
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  Validators,
} from '@angular/forms';

const PHONE_PATTERN = /^[0-9+()\-.\s]{6,20}$/;

export interface Mission {
  title: string;
  description: string;
}

export interface BenevoleFormValue {
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  age: number;
  availability: boolean[];
  missions: boolean[];
  motivation: string;
  acceptRules: boolean;
}

@Component({
  selector: 'app-nous-rejoindre',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './nous-rejoindre.html',
  styleUrl: './nous-rejoindre.css',
})
export class NousRejoindre {
  submitted = false;

  readonly missions: Mission[] = [
    {
      title: 'Câlins & socialisation',
      description: "Passer du temps avec les animaux pour les habituer au contact humain avant leur adoption.",
    },
    {
      title: 'Promenades',
      description: 'Sortir les chiens du refuge au quotidien, seul·e ou en binôme.',
    },
    {
      title: 'Transport',
      description: "Accompagner les animaux chez le vétérinaire ou vers leur famille d'accueil.",
    },
    {
      title: 'Communication',
      description: "Rédiger les fiches animaux et animer les réseaux sociaux du refuge.",
    },
    {
      title: 'Événements',
      description: 'Tenir un stand lors des journées portes ouvertes et marchés locaux.',
    },
    {
      title: 'Entretien',
      description: 'Nettoyage des espaces de vie et distribution des repas.',
    },
  ];

  readonly engagement: string[] = [
    'Être majeur·e (ou mineur·e accompagné·e d\'un responsable légal)',
    'Disponibilité minimum : une demi-journée par semaine',
    'Engagement minimum de 3 mois',
    "Passer un entretien de 15 minutes avant intégration",
    'Respecter le règlement intérieur du refuge',
  ];

  readonly availabilityOptions = ['Semaine', 'Week-end', 'Vacances scolaires'];

  readonly benevoleForm: FormGroup;

  constructor(private readonly fb: FormBuilder) {
    this.benevoleForm = this.fb.group({
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [optionalPhoneValidator]],
      age: [null, [Validators.required, Validators.min(16), Validators.max(120)]],
      availability: this.fb.array(
        this.availabilityOptions.map(() => false),
        [atLeastOneChecked]
      ),
      missions: this.fb.array(
        this.missions.map(() => false),
        [atLeastOneChecked]
      ),
      motivation: ['', [Validators.required, Validators.minLength(20)]],
      acceptRules: [false, [Validators.requiredTrue]],
    });
  }

  get availabilityArray(): FormArray {
    return this.benevoleForm.get('availability') as FormArray;
  }

  get missionsArray(): FormArray {
    return this.benevoleForm.get('missions') as FormArray;
  }

  isInvalid(controlName: keyof BenevoleFormValue): boolean {
    const control = this.benevoleForm.get(controlName as string);
    return !!control && control.invalid && (control.touched || control.dirty);
  }

  onSubmit(): void {
    if (this.benevoleForm.invalid) {
      this.benevoleForm.markAllAsTouched();
      this.submitted = false;
      return;
    }

    const raw = this.benevoleForm.value;
    const value: BenevoleFormValue = {
      ...raw,
      availability: this.availabilityOptions.filter((_, i) => raw.availability[i]),
      missions: this.missions.map((m) => m.title).filter((_, i) => raw.missions[i]),
    } as unknown as BenevoleFormValue;

    // Hand-off point: wire this up to a real API call (e.g. HttpClient.post).
    console.log('Candidature bénévole envoyée', value);

    this.submitted = true;
    this.benevoleForm.reset();
    this.availabilityArray.controls.forEach((c) => c.setValue(false));
    this.missionsArray.controls.forEach((c) => c.setValue(false));
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

/** Validator for a FormArray of booleans: at least one must be true. */
export function atLeastOneChecked(control: AbstractControl): ValidationErrors | null {
  const array = control as FormArray;
  const hasOne = array.controls?.some((c) => c.value === true);
  return hasOne ? null : { atLeastOne: true };
}
