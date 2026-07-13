import { Chien } from './../../model/chien';
import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import {
  BehaviorSubject,
  combineLatest,
  debounceTime,
  distinctUntilChanged,
  map,
  Observable,
  startWith,
  Subject,
  switchMap,
} from 'rxjs';
import { Caractere } from '../../enum/caractere';
import { Famille } from '../../enum/famille';
import { Genre } from '../../enum/genre';
import { Statut } from '../../enum/statut';
import { TypeAnimal } from '../../enum/type-animal';
import { Animal } from '../../model/statut-animal';
import { AnimalService } from '../../service/animal-service';
import { QuackShelterService } from '../../service/quack-shelter-service';
import { QuackShelter } from '../../model/quack-shelter';

@Component({
  selector: 'app-nos-animaux-page',
  imports: [FormsModule, ReactiveFormsModule, CommonModule],
  templateUrl: './nos-animaux-page.html',
  styleUrl: './nos-animaux-page.css',
})
export class NosAnimauxPage implements OnInit {
  private titleService: Title = inject(Title);
  private animalSrv: AnimalService = inject(AnimalService);
  private quackShelterSrv: QuackShelterService = inject(QuackShelterService);

  private refresh$: Subject<void> = new Subject<void>();
  protected animaux$!: Observable<Animal[]>;
  protected animauxFiltres$!: Observable<Animal[]>;

  protected typeAnimalValues: TypeAnimal[] = Object.values(TypeAnimal);
  protected familleValues: Famille[] = Object.values(Famille);
  protected genreValues: Genre[] = Object.values(Genre);
  protected caractereValues: Caractere[] = Object.values(Caractere);
  protected statutValues: Statut[] = Object.values(Statut);
  protected quackShelters$!: Observable<QuackShelter[]>;

  protected afficherFiltre = false;
  protected famillesFiltre = new Set<Famille>();
  protected genresFiltre = new Set<Genre>();
  protected caracteresFiltre = new Set<Caractere>();
  protected statutsFiltre = new Set<Statut>();
  private filtreChange$ = new BehaviorSubject<void>(undefined);

  // ===== Recherche =====
  protected rechercheControl = new FormControl<string>('', { nonNullable: true });
  private recherche$!: Observable<string>;

  private readonly nbImagesParType: Record<string, number> = {
    [TypeAnimal.CHAT]: 5,
    [TypeAnimal.CANARD]: 5,
    [TypeAnimal.CHIEN]: 5,
    [TypeAnimal.POULE]: 3,
    [TypeAnimal.NAC]: 5,
  };

  ngOnInit(): void {
    this.titleService.setTitle('Gestion des Animaux du Shelter');

    this.animaux$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.animalSrv.findAll()),
    );

    this.recherche$ = this.rechercheControl.valueChanges.pipe(
      startWith(this.rechercheControl.value),
      debounceTime(200),
      distinctUntilChanged(),
      map((valeur) => (valeur ?? '').trim().toLowerCase()),
    );

    this.animauxFiltres$ = combineLatest([this.animaux$, this.filtreChange$, this.recherche$]).pipe(
      map(([animaux, , recherche]) =>
        animaux.filter(
          (a) => this.correspondAuxFiltres(a) && this.correspondALaRecherche(a, recherche),
        ),
      ),
    );

    this.quackShelters$ = this.quackShelterSrv.findAll();
  }




  private imageCache = new Map<string, string>();

  protected getImageAnimal(a: Animal): string {
    const cle = String(a.id);

    if (!this.imageCache.has(cle)) {
      const type = a.typeAnimal;
      const nb = this.nbImagesParType[type] ?? 1;
      const index = Math.floor(Math.random() * nb) + 1;
      const dossier = type; // ex: "Chat"
      const prefixe = type.toString().toLowerCase(); // ex: "chat"

      this.imageCache.set(cle, `assets/image/animaux/${dossier}/${prefixe}${index}.png`);
    }

    return this.imageCache.get(cle)!;
  }





  private correspondAuxFiltres(a: Animal): boolean {
    const matchFamille = this.famillesFiltre.size === 0 || this.famillesFiltre.has(a.famille);
    const matchGenre = this.genresFiltre.size === 0 || this.genresFiltre.has(a.genre);
    const matchCaracteres =
      this.caracteresFiltre.size === 0 || a.caracteres?.some((c) => this.caracteresFiltre.has(c));
    const matchStatut =
      this.statutsFiltre.size === 0 ||
      (a.statutAnimal && this.statutsFiltre.has(a.statutAnimal.statut));

    return matchFamille && matchGenre && matchCaracteres && matchStatut;
  }

  private correspondALaRecherche(a: Animal, recherche: string): boolean {
    if (!recherche) return true;
    return a.nomAnimal.toLowerCase().includes(recherche);
  }

  private reload() {
    this.refresh$.next();
  }

  private appliquerFiltres(): void {
    this.filtreChange$.next();
  }

  // ===== Ajouter des valeurs dans les filtres ==============

  protected toggleFamilleFiltre(f: Famille): void {
    this.toggleSet(this.famillesFiltre, f);
    this.appliquerFiltres();
  }
  protected toggleGenreFiltre(g: Genre): void {
    this.toggleSet(this.genresFiltre, g);
    this.appliquerFiltres();
  }
  protected toggleCaractereFiltre(c: Caractere): void {
    this.toggleSet(this.caracteresFiltre, c);
    this.appliquerFiltres();
  }
  protected toggleStatutFiltre(s: Statut): void {
    this.toggleSet(this.statutsFiltre, s);
    this.appliquerFiltres();
  }

  protected reinitialiserFiltres(): void {
    this.famillesFiltre.clear();
    this.genresFiltre.clear();
    this.caracteresFiltre.clear();
    this.statutsFiltre.clear();
    this.rechercheControl.setValue('');
    this.appliquerFiltres();
  }

  private toggleSet<T>(set: Set<T>, valeur: T): void {
    set.has(valeur) ? set.delete(valeur) : set.add(valeur);
  }

  protected afficherStatut(a: Animal): string {
    if (!a.statutAnimal) return '-';
    const statut = a.statutAnimal.statut;
    if (statut === Statut.Adopte || statut === Statut.Mort) {
      return statut;
    }
    return `${statut} - ${a.statutAnimal.emplacement?.id ?? '?'}`;
  }

  toggleFiltres(): void {
    this.afficherFiltre = !this.afficherFiltre;
  }

  calculAge(d: Date | string): number {
  const MS_PAR_AN = 365.25 * 24 * 60 * 60 * 1000; // prise en compte des années bissextiles
  const date = d instanceof Date ? d : new Date(d);
  return (Date.now() - date.getTime()) / MS_PAR_AN;
}

  cleanAge(age: number): number {
    return Math.floor(age);
  }

  convertAnneeEnMois(age: number): number {
    return Math.floor(age * 12);
  }
}
