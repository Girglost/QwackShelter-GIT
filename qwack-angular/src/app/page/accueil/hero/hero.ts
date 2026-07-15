import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { Observable } from 'rxjs/internal/Observable';
import { AnimalService } from '../../../service/animal-service';
import { AuthService } from '../../../service/auth-service';
import { Genre } from './../../../enum/genre';
import { Animal } from './../../../model/animal';

import { getImageAnimal } from '../../../utils/animal-image-utils.utils';
const PHOTO_PLACEHOLDER = 'assets/image/animaux/placeholder.png';

@Component({
  selector: 'app-hero',
  imports: [CommonModule, RouterLink],
  templateUrl: './hero.html',
  styleUrl: './hero.css',
})
export class Hero implements OnInit {
  animalDuJour = signal<Animal | null>(null);

  constructor(
    private authService: AuthService,
    private animalSrv: AnimalService,
    private router: Router,
  ) { }

  ngOnInit(): void {
    console.log('ngOnInit Hero appelé');

    this.trouverAnimaux().subscribe({
      next: (animaux) => {
        console.log('Animaux reçus :', animaux);

        if (!animaux || animaux.length === 0) {
          console.warn("Aucun animal reçu depuis l'API.");
          return;
        }
        const index = Math.floor(Math.random() * animaux.length);
        this.animalDuJour.set(animaux[index]);
        console.log('Animal du jour sélectionné :', JSON.stringify(this.animalDuJour(), null, 2));
      },
      error: (err) => {
        console.error("Erreur lors du chargement de l'animal du jour :", err);
      },
      complete: () => {
        console.log('Requête findAll terminée');
      },
    });
  }

  private trouverAnimaux(): Observable<Animal[]> {
    return this.animalSrv.findAll();
  }

  // La photo n'existe pas encore dans le modèle Animal : placeholder en attendant.
  get photoAnimal(): string {
    return PHOTO_PLACEHOLDER;
  }

  // Pas de champ "description" dans le modèle : on résume à partir des caractères.
  get descriptionAnimal(): string {
    const caracteres = this.animalDuJour()?.caracteres ?? [];
    return caracteres.length > 0 ? caracteres.join(', ') : '';
  }

  get genreLabel(): string {
    // À vérifier selon les valeurs exactes de l'enum Genre
    switch (this.animalDuJour()?.genre) {
      case Genre.Male:
        return 'Mâle';
      case Genre.Femelle:
        return 'Femelle';
      default:
        return '';
    }
  }

  get ageAnimal(): number | null {
    const dateNaissance = this.animalDuJour()?.dateNaissance;
    if (!dateNaissance) {
      return null;
    }

    const naissance = new Date(dateNaissance);
    const aujourdHui = new Date();

    let age = aujourdHui.getFullYear() - naissance.getFullYear();
    const moisDiff = aujourdHui.getMonth() - naissance.getMonth();

    if (moisDiff < 0 || (moisDiff === 0 && aujourdHui.getDate() < naissance.getDate())) {
      age--;
    }

    return age;
  }

  get ageLabel(): string {
    const age = this.ageAnimal;
    if (age === null) {
      return '';
    }
    if (age < 1) {
      return '<1 an';
    }
    return `${age} an${age > 1 ? 's' : ''}`;
  }

  voirProfil(): void {
    if (this.animalDuJour()) {
      this.router.navigate(['/profil-animal', this.animalDuJour()?.id]);
    }
  }

  protected getImageAnimal(a: Animal): string {
    return getImageAnimal(a);
  }
}
