import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { Animal } from '../../model/animal';
import { VisiteRequest } from '../../model/visite-request';
import { AnimalService } from '../../service/animal-service';
import { AuthService } from '../../service/auth-service';
import { VisiteService } from '../../service/visite-service';

@Component({
  selector: 'app-demande-visite',
  imports: [ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './demande-visite.html',
  styleUrl: './demande-visite.css',
})
export class DemandeVisite implements OnInit {

  protected animauxPresent$!: Observable<Animal[]>;

  private router: Router = inject(Router);

  private authService = inject(AuthService);
  private animalService = inject(AnimalService);

  adoptionForm: FormGroup;

  constructor(private fb: FormBuilder, private visiteService: VisiteService) {
    this.adoptionForm = this.fb.group({

      nom: [{ value: '', disabled: true }, Validators.required],
      prenom: [{ value: '', disabled: true }, Validators.required],
      email: [{ value: '', disabled: true }, Validators.required],
      telephone: [''],
      animalId: ['', Validators.required],
      dateVisite: ['', Validators.required]

    });
  }

  onSubmit() {

    if (this.adoptionForm.invalid) {
      return;
    }

    const user = this.authService.currentUser();

    if (!user) {
      return;
    }

    const demande: VisiteRequest = {
      idAnimal: this.adoptionForm.value.animalId,
      dateVisite: this.adoptionForm.value.dateVisite
    }


    this.visiteService.demanderVisite(demande)
      .subscribe({
        next: response => {
          console.log(
            "Demande envoyée",
            response
          );
          alert("Votre demande de visite a été envoyée ! ")
          this.router.navigate(['/accueil']);
        },
        error: err => {

          console.error(err);

        }
      });

    this.adoptionForm.reset();



  }


  ngOnInit() {
    this.animauxPresent$ = this.animalService.findPresent();

    const user = this.authService.currentUser();
    if (user) {

      this.adoptionForm.patchValue({

        nom: user.nom,
        prenom: user.prenom,
        email: user.login,

      });

    }
  }
}
