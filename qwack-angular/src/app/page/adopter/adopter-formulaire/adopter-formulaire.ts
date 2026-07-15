import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { AdoptionRequest } from '../../../model/adoption-request';
import { Animal } from '../../../model/animal';
import { AdoptionService } from '../../../service/adoption-service';
import { AnimalService } from '../../../service/animal-service';
import { AuthService } from '../../../service/auth-service';

@Component({
  selector: 'app-adoption-form',
  standalone: true,
  imports: [ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './adopter-formulaire.html',
  styleUrls: ['./adopter-formulaire.css']
})
export class AdoptionForm implements OnInit {

  protected animauxPresent$!: Observable<Animal[]>;

  private router: Router = inject(Router);

  private authService = inject(AuthService);
  private adoptionService = inject(AdoptionService);

  // c'est lui qui va demander une adoption
  currentUser = this.authService.currentUser;

  adoptionForm: FormGroup;

  constructor(private fb: FormBuilder, private animalService: AnimalService) {
    this.adoptionForm = this.fb.group({

      nom: [{ value: '', disabled: true }, Validators.required],
      prenom: [{ value: '', disabled: true }, Validators.required],
      email: [{ value: '', disabled: true }, Validators.required],
      telephone: [''],
      adresse: [{ value: '', disabled: true }, Validators.required],
      logement: [{ value: '', disabled: true }, Validators.required],

      animalId: ['', Validators.required],

      jardin: ['Oui', Validators.required],
      motivation: ['', Validators.required]

    });
  }

  onSubmit() {

    if (this.adoptionForm.valid) {

      const demande: AdoptionRequest = {
        idAnimal: this.adoptionForm.value.animalId,
      }

      this.adoptionService.demanderAdoption(demande)
        .subscribe({
          next: response => {
            console.log(
              "Demande envoyée",
              response
            );
            alert("Votre demande d'adoption a été envoyée ! ")
            this.router.navigate(['/accueil']);
          },
          error: err => {

            console.error(err);

          }
        });

      this.adoptionForm.reset();

    }

  }


  ngOnInit() {
    this.animauxPresent$ = this.animalService.findPresent();

    const user = this.authService.currentUser();
    if (user) {

      console.log("TYPE HABITATION :", user.habitation?.typeLieu);

      this.adoptionForm.patchValue({

        nom: user.nom,
        prenom: user.prenom,
        email: user.login,
        adresse: `${user.habitation?.adresse.numero} ${user.habitation?.adresse.voie}, ${user.habitation?.adresse.cp} ${user.habitation?.adresse.ville}`,
        logement: user.habitation?.typeLieu

      });

    }
  }
}
