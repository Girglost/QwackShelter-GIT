import { Component, inject, OnInit } from '@angular/core';
import { Router,RouterLink } from '@angular/router';
import {Observable } from 'rxjs/internal/Observable';
import { Animal } from './../../../model/animal';
import { AnimalService } from '../../../service/animal-service';

@Component({
  selector: 'app-hero',
  imports: [RouterLink],
  templateUrl: './hero.html',
  styleUrl: './hero.css',
})
export class Hero implements OnInit {

  private animalSrv: AnimalService = inject(AnimalService);
  private router: Router = inject(Router);

  animalDuJour: Animal | null = null;

ngOnInit(): void {
  console.log("Début chargement animal");

  this.trouverAnimal().subscribe({
    next: (animal) => {
      console.log("Animal reçu :", animal);
      this.animalDuJour = animal;
    },
    error: (err) => {
      console.error("Erreur API :", err);
    },
    complete: () => {
      console.log("Chargement terminé");
    }
  });
}

private trouverAnimal(): Observable<Animal> {
  const id = 1;

  const resultat = this.animalSrv.findById(id);

  console.log("Observable créé :", resultat);

  return resultat;
}

  voirProfil(): void {
      alert("clic");
        console.log("animal actuel :", this.animalDuJour);
    if (this.animalDuJour) {
      this.router.navigate(['/animal', this.animalDuJour.id]);
    }
  }
}
