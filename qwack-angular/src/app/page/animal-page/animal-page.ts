import { CreateStatutAnimalRequest } from './../../model/create-statut-animal-request';
import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Title } from '@angular/platform-browser';
import {
  BehaviorSubject,
  combineLatest,
  map,
  Observable,
  startWith,
  Subject,
  switchMap,
} from 'rxjs';
import { Animal } from '../../model/animal';
import { AnimalService } from '../../service/animal-service';
import { Famille } from '../../enum/famille';
import { Genre } from '../../enum/genre';
import { Caractere } from '../../enum/caractere';
import { Statut } from '../../enum/statut';
import { TypeAnimal } from '../../enum/type-animal';
import { QuackShelter } from '../../model/quack-shelter';
import { QuackShelterService } from '../../service/quack-shelter-service';
import { CreateAnimalRequest } from '../../model/create-animal-request';
import { UpdateAnimalRequest } from '../../model/update-animal-request';
import { Emplacement } from '../../model/emplacement';
import { EmplacementService } from '../../service/emplacement-service';
import { StatutAnimalService } from '../../service/statut-animal-service';

@Component({
  selector: 'app-animal-page',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './animal-page.html',
  styleUrl: './animal-page.css',
})
export class AnimalPage implements OnInit {
  private titleService: Title = inject(Title);
  private animalSrv: AnimalService = inject(AnimalService);
  private quackShelterSrv: QuackShelterService = inject(QuackShelterService);
  private EmpSrv: EmplacementService = inject(EmplacementService);
  private statutSrv: StatutAnimalService = inject(StatutAnimalService);

  private refresh$: Subject<void> = new Subject<void>();
  private animaux$!: Observable<Animal[]>;
  protected animauxFiltres$!: Observable<Animal[]>;

  protected typeAnimalValues: TypeAnimal[] = Object.values(TypeAnimal);
  protected familleValues: Famille[] = Object.values(Famille);
  protected genreValues: Genre[] = Object.values(Genre);
  protected caractereValues: Caractere[] = Object.values(Caractere);
  protected statutValues: Statut[] = Object.values(Statut);
  protected quackShelters$!: Observable<QuackShelter[]>;
  protected emplacements$!: Observable<Emplacement[]>;
  protected selectedFamille!: Famille;

  // --- Filtres combinables ---
  protected famillesFiltre = new Set<Famille>();
  protected genresFiltre = new Set<Genre>();
  protected caracteresFiltre = new Set<Caractere>();
  protected statutsFiltre = new Set<Statut>();
  private filtreChange$ = new BehaviorSubject<void>(undefined);

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formAnimal!: FormGroup;

  // Champs communs
  protected CtrlTypeAnimal!: FormControl;
  protected CtrlNomAnimal!: FormControl;
  protected CtrlDateNaissance!: FormControl;
  protected CtrlCouleur!: FormControl;
  protected CtrlRegimeAlimentaire!: FormControl;
  protected CtrlTraitement!: FormControl;
  protected CtrlFamille!: FormControl;
  protected CtrlGenre!: FormControl;
  protected CtrlQuackShelter!: FormControl;
  protected CtrlEmplacement!: FormControl;

  // Champs spécifiques Chat/Chien/NAC
  protected CtrlRace!: FormControl;
  protected CtrlSterilisation!: FormControl;
  protected CtrlGestante!: FormControl;

  // Champs spécifiques Canard/Poule
  protected CtrlCapaciteVol!: FormControl;
  protected CtrlPondeuse!: FormControl;
  protected CtrlEstSauvage!: FormControl;

  protected caracteresSelectionnes: Caractere[] = [];
  protected editingAnimal: Animal | null = null;

  ngOnInit(): void {
    this.titleService.setTitle('Gestion des Animaux du Shelter');

    this.animaux$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.animalSrv.findAll()),
    );

    this.animauxFiltres$ = combineLatest([this.animaux$, this.filtreChange$]).pipe(
      map(([animaux]) => animaux.filter((a) => this.correspondAuxFiltres(a))),
    );

    this.quackShelters$ = this.quackShelterSrv.findAll();
    this.emplacements$ = this.EmpSrv.findAll();

    this.animaux$.subscribe((animaux) => console.log(animaux));

    this.quackShelters$.subscribe((q) => console.log(q));

    this.emplacements$.subscribe((emplacements) => console.log(emplacements));

    this.CtrlTypeAnimal = this.formBuilder.control('', Validators.required);
    this.CtrlNomAnimal = this.formBuilder.control('', Validators.required);
    this.CtrlDateNaissance = this.formBuilder.control('', Validators.required);
    this.CtrlCouleur = this.formBuilder.control('', Validators.required);
    this.CtrlRegimeAlimentaire = this.formBuilder.control('');
    this.CtrlTraitement = this.formBuilder.control('');
    this.CtrlGenre = this.formBuilder.control('', Validators.required);
    this.CtrlQuackShelter = this.formBuilder.control('', Validators.required);
    this.CtrlEmplacement = this.formBuilder.control('', Validators.required);
    this.CtrlFamille = this.formBuilder.control('');

    this.CtrlRace = this.formBuilder.control('');
    this.CtrlSterilisation = this.formBuilder.control(false);
    this.CtrlGestante = this.formBuilder.control(false);
    this.CtrlCapaciteVol = this.formBuilder.control(false);
    this.CtrlPondeuse = this.formBuilder.control(false);
    this.CtrlEstSauvage = this.formBuilder.control(false);

    this.formAnimal = this.formBuilder.group({
      typeAnimal: this.CtrlTypeAnimal,
      nomAnimal: this.CtrlNomAnimal,
      dateNaissance: this.CtrlDateNaissance,
      couleur: this.CtrlCouleur,
      regimeAlimentaire: this.CtrlRegimeAlimentaire,
      traitement: this.CtrlTraitement,
      famille: this.CtrlFamille,
      genre: this.CtrlGenre,
      quackShelter: this.CtrlQuackShelter,
      emplacement: this.CtrlEmplacement,
      race: this.CtrlRace,
      sterilisation: this.CtrlSterilisation,
      gestante: this.CtrlGestante,
      capaciteVol: this.CtrlCapaciteVol,
      pondeuse: this.CtrlPondeuse,
      estSauvage: this.CtrlEstSauvage,
    });

    this.CtrlTypeAnimal.valueChanges.subscribe(() => this.ajusterValidateurs());
  }

  private ajusterValidateurs(): void {
    const type = this.CtrlTypeAnimal.value as TypeAnimal;
    const estDomestique =
      type === TypeAnimal.Chat || type === TypeAnimal.Chien || type === TypeAnimal.NAC;
    const estVolaille = type === TypeAnimal.Canard || type === TypeAnimal.Poule;

    if (estDomestique || estVolaille) {
      this.CtrlRace.setValidators(Validators.required);
    } else {
      this.CtrlRace.clearValidators();
    }
    this.CtrlRace.updateValueAndValidity();
  }

  protected get estDomestique(): boolean {
    const type = this.CtrlTypeAnimal.value;
    return type === TypeAnimal.Chat || type === TypeAnimal.Chien || type === TypeAnimal.NAC;
  }

  protected get estCanard(): boolean {
    return this.CtrlTypeAnimal.value === TypeAnimal.Canard;
  }

  protected get estPoule(): boolean {
    return this.CtrlTypeAnimal.value === TypeAnimal.Poule;
  }

  protected get estVolaille(): boolean {
    return this.estCanard || this.estPoule;
  }

  // ===== Sert a verif si l'Animal en question respecte tous les filtres  ==============

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
    this.appliquerFiltres();
  }

  // ===== Permet de d'ajouter/retirer un filtre ==============

  private toggleSet<T>(set: Set<T>, valeur: T): void {
    set.has(valeur) ? set.delete(valeur) : set.add(valeur);
  }

  protected toggleCaractereForm(c: Caractere): void {
    const index = this.caracteresSelectionnes.indexOf(c);
    if (index >= 0) {
      this.caracteresSelectionnes.splice(index, 1);
    } else {
      this.caracteresSelectionnes.push(c);
    }
  }

  protected addOrUpdate() {
    const v = this.formAnimal.getRawValue();

    const commun = {
      nomAnimal: v.nomAnimal,
      dateNaissance: v.dateNaissance,
      couleur: v.couleur,
      regimeAlimentaire: v.regimeAlimentaire,
      traitement: v.traitement,
      genre: v.genre,
      caracteres: this.caracteresSelectionnes,
      qwackShelterId: v.quackShelter?.id, // <-- CORRIGÉ : on extrait l'id de l'objet QuackShelter
      capaciteVol: v.capaciteVol,
      pondeuse: v.pondeuse,
      race: v.race,
      emplacement: v.emplacement,
      sterilisation: v.sterilisation,
      gestante: v.gestante,
      estSauvage: v.estSauvage,
      espece: v.typeAnimal,
      type_animal: v.typeAnimal, // <-- utilisé pour router vers le bon contrôleur
    };

    if (this.editingAnimal) {
      const req: UpdateAnimalRequest = commun;
      this.animalSrv
        .update(this.editingAnimal.id, req, v.typeAnimal)
        .subscribe(() => this.reload());
    } else {
      const req: CreateAnimalRequest = { ...commun, famille: this.defaultFamille(v)! };
      console.log(req);
      this.animalSrv.add(req).subscribe((animal) => {
        console.log("animal",animal);
        const s: CreateStatutAnimalRequest = {
          emplacementId: req.emplacement,
          animalId: animal.id,
        };

        console.log('SA', s);

        this.statutSrv.add(s).subscribe(() => this.reload());
        // this.animalSrv.add(req).subscribe(() => this.reload());
      });
    }
    this.annulerEdition();
  }

  protected defaultFamille(a: Animal): Famille | undefined {
    if (a.typeAnimal === TypeAnimal.Chat) {
      return Famille.Felin;
    }
    if (a.typeAnimal === TypeAnimal.Chien) {
      return Famille.Canin;
    }
    if (a.typeAnimal === TypeAnimal.NAC) {
      return Famille.Muscilide;
    }
    if (a.typeAnimal === TypeAnimal.Canard || a.typeAnimal === TypeAnimal.Poule) {
      return Famille.Galide;
    }
    return undefined;
  }

  protected edit(a: Animal) {
    this.editingAnimal = a;
    this.CtrlTypeAnimal.disable();
    this.CtrlNomAnimal.setValue(a.nomAnimal);
    this.CtrlDateNaissance.setValue(a.dateNaissance);
    this.CtrlCouleur.setValue(a.couleur);
    this.CtrlRegimeAlimentaire.setValue(a.regimeAlimentaire);
    this.CtrlTraitement.setValue(a.traitement);
    this.CtrlFamille.disable();
    this.CtrlGenre.setValue(a.genre);
    this.CtrlQuackShelter.setValue(a.quackShelterId);
    this.CtrlRace.setValue(a.race ?? '');
    this.CtrlSterilisation.setValue(a.sterilisation ?? false);
    this.CtrlGestante.setValue(a.gestante ?? false);
    this.CtrlCapaciteVol.setValue(a.capaciteVol ?? false);
    this.CtrlPondeuse.setValue(a.pondeuse ?? false);
    this.CtrlEstSauvage.setValue(a.estSauvage ?? false);
    this.caracteresSelectionnes = [...(a.caracteres ?? [])];
  }

  protected annulerEdition(): void {
    this.editingAnimal = null;
    this.CtrlTypeAnimal.enable();
    this.CtrlFamille.enable();
    this.formAnimal.reset({
      sterilisation: false,
      gestante: false,
      capaciteVol: false,
      pondeuse: false,
      estSauvage: false,
    });
    this.caracteresSelectionnes = [];
  }

  protected remove(a: Animal) {
    this.animalSrv.remove(a.id).subscribe(() => this.reload());
  }

  protected afficherStatut(a: Animal): string {
    if (!a.statutAnimal) return '-';
    const statut = a.statutAnimal.statut;
    if (statut === Statut.Adopte || statut === Statut.Mort) {
      return statut;
    }
    return `${statut} - ${a.statutAnimal.emplacement.id ?? '?'}`;
  }
}
