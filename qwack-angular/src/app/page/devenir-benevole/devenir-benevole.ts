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
  availability: string[];
  missions: string[];
  motivation: string;
  acceptRules: boolean;
}

@Component({
  selector: 'app-devenir-benevole',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './devenir-benevole.html',
  styleUrl: './devenir-benevole.css',
})
export class DevenirBenevole {

  submitted = false;

  readonly missions: Mission[] = [
    {
      title: 'Promenade des chiens',
      description: 'Offrir aux chiens des sorties régulières et des moments de détente.'
    },
    {
      title: 'Socialisation des chats',
      description: 'Jouer, câliner et rassurer les chats afin de faciliter leur adoption.'
    },
    {
      title: 'Transport',
      description: 'Accompagner les animaux chez le vétérinaire ou dans leur famille d’accueil.'
    },
    {
      title: 'Événements',
      description: 'Participer aux journées d’adoption, collectes et animations.'
    },
    {
      title: 'Communication',
      description: 'Aider à alimenter les réseaux sociaux et créer des supports de communication.'
    },
    {
      title: 'Entretien',
      description: 'Participer au nettoyage des locaux et au bien-être quotidien des animaux.'
    }
  ];

  readonly engagement: string[] = [
    'Être majeur ou accompagné d’un responsable légal.',
    'Pouvoir consacrer au minimum quelques heures par mois.',
    'Respecter les animaux, les bénévoles et le règlement du refuge.',
    'Participer à une courte présentation et formation avant de commencer.'
  ];

  readonly availabilityOptions = [
    'En semaine',
    'Le week-end',
    'Vacances scolaires'
  ];

  readonly benevoleForm: FormGroup;

  constructor(private readonly fb: FormBuilder) {

    this.benevoleForm = this.fb.group({

      firstName: ['', Validators.required],

      lastName: ['', Validators.required],

      email: ['', [Validators.required, Validators.email]],

      phone: ['', optionalPhoneValidator],

      age: [
        null,
        [
          Validators.required,
          Validators.min(16),
          Validators.max(120)
        ]
      ],

      availability: this.fb.array(
        this.availabilityOptions.map(() => false),
        atLeastOneChecked
      ),

      missions: this.fb.array(
        this.missions.map(() => false),
        atLeastOneChecked
      ),

      motivation: [
        '',
        [
          Validators.required,
          Validators.minLength(20)
        ]
      ],

      acceptRules: [
        false,
        Validators.requiredTrue
      ]

    });

  }

  get availabilityArray(): FormArray {
    return this.benevoleForm.get('availability') as FormArray;
  }

  get missionsArray(): FormArray {
    return this.benevoleForm.get('missions') as FormArray;
  }

  isInvalid(controlName: keyof BenevoleFormValue): boolean {

    const control = this.benevoleForm.get(controlName);

    return !!control &&
      control.invalid &&
      (control.dirty || control.touched);

  }

  onSubmit(): void {

    if (this.benevoleForm.invalid) {

      this.benevoleForm.markAllAsTouched();
      this.submitted = false;
      return;

    }

    const raw = this.benevoleForm.value;

    const candidature = {

      ...raw,

      availability: this.availabilityOptions.filter(
        (_, i) => raw.availability[i]
      ),

      missions: this.missions
        .map(m => m.title)
        .filter((_, i) => raw.missions[i])

    };

    // Ici tu pourras appeler ton API plus tard.
    console.log(candidature);

    this.submitted = true;

    this.benevoleForm.reset();

    this.availabilityArray.controls.forEach(c => c.setValue(false));

    this.missionsArray.controls.forEach(c => c.setValue(false));

  }

}

export function optionalPhoneValidator(
  control: AbstractControl
): ValidationErrors | null {

  const value = control.value;

  if (!value) {
    return null;
  }

  return PHONE_PATTERN.test(value)
    ? null
    : { invalidPhone: true };

}

export function atLeastOneChecked(
  control: AbstractControl
): ValidationErrors | null {

  const array = control as FormArray;

  return array.controls.some(c => c.value)
    ? null
    : { atLeastOne: true };

}
