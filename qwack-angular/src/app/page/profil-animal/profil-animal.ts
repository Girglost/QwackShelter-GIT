import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Observable, switchMap } from 'rxjs';
import { Animal } from '../../model/animal';
import { AnimalService } from '../../service/animal-service';
import { getImageAnimal } from '../../utils/animal-image-utils.utils';

@Component({
  selector: 'app-profil-animal-page',
  imports: [CommonModule, RouterLink],
  templateUrl: './profil-animal.html',
  styleUrl: './profil-animal.css',
})
export class ProfilAnimal implements OnInit {
  private titleService: Title = inject(Title);
  private animalSrv: AnimalService = inject(AnimalService);
  private route: ActivatedRoute = inject(ActivatedRoute);

  protected animal$!: Observable<Animal>;

  ngOnInit(): void {
    this.animal$ = this.route.paramMap.pipe(
      switchMap((params) => {
        const id = parseInt(params.get('id')!, 10);
        if (isNaN(id)) {
          throw new Error('Id invalide');
        }
        return this.animalSrv.findById(id);
      }),
    );

    this.animal$.subscribe((a) => {
      this.titleService.setTitle(`${a.nomAnimal} - Profil`);
    });
  }

  protected getImageAnimal(a: Animal): string {
    return getImageAnimal(a);
  }

  protected calculAge(d: Date | string): number {
    const MS_PAR_AN = 365.25 * 24 * 60 * 60 * 1000;
    const date = d instanceof Date ? d : new Date(d);
    return (Date.now() - date.getTime()) / MS_PAR_AN;
  }

  protected cleanAge(age: number): number {
    return Math.floor(age);
  }

  protected convertAnneeEnMois(age: number): number {
    return Math.floor(age * 12);
  }

  protected formatDateNaissance(d: Date | string): Date {
    return d instanceof Date ? d : new Date(d);
  }
}
