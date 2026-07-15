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
import { merge, Observable, startWith, Subject, switchMap } from 'rxjs';
import { StatutAnimal } from '../../model/statut-animal';
import { StatutAnimalService } from '../../service/statut-animal-service';
import { Statut } from '../../enum/statut';
import { StatutValidation } from '../../enum/statut-validation';
import { Emplacement } from '../../model/emplacement';
import { Animal } from '../../model/animal';
import { EmplacementService } from '../../service/emplacement-service';
import { AnimalService } from '../../service/animal-service';
import { CreateStatutAnimalRequest } from '../../model/create-statut-animal-request';

@Component({
  selector: 'app-statut-animal-page',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './statut-animal-page.html',
  styleUrl: './statut-animal-page.css',
})
export class StatutAnimalPage implements OnInit {
  private titleService: Title = inject(Title);
  private sAnimalSrv: StatutAnimalService = inject(StatutAnimalService);
  private emplacementSrv: EmplacementService = inject(EmplacementService);
  private animalSrv: AnimalService = inject(AnimalService);

  private refresh$: Subject<void> = new Subject<void>();
  protected sAnimals$!: Observable<StatutAnimal[]>;

  protected statutValues: string[] = Object.values(Statut);
  protected statutAdoptionValues: string[] = Object.values(StatutValidation);

  protected emplacements$!: Observable<Emplacement[]>;
  protected animaux$!: Observable<Animal[]>;

  // --- Filtre par statut ---
  protected statutFiltre: string | null = null;
  private filtreChange$ = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formStatutAnimal!: FormGroup;

  // Toujours présents (ajout + modification)
  protected CtrlEmplacement!: FormControl;
  protected CtrlAnimal!: FormControl;

  // Uniquement en modification
  protected CtrlDateArrivee!: FormControl;
  protected CtrlDateDepart!: FormControl;
  protected CtrlStatut!: FormControl;
  protected CtrlStatutAdoption!: FormControl;

  protected editingStatutAnimalId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle('Gestion des StatutAnimals du Shelter');

    this.sAnimals$ = merge(this.refresh$, this.filtreChange$).pipe(
      startWith(0),
      switchMap(() => this.getStatutAnimauxSelonFiltre()),
    );

    this.emplacements$ = this.emplacementSrv.findAll();
    this.animaux$ = this.animalSrv.findAll();

    this.sAnimals$.subscribe((s) => console.log(s));

    this.animaux$.subscribe((animaux) => console.log(animaux));

    this.emplacements$.subscribe((emplacements) => console.log(emplacements));

    this.CtrlEmplacement = this.formBuilder.control('', Validators.required);
    this.CtrlAnimal = this.formBuilder.control('', Validators.required);

    this.CtrlDateArrivee = this.formBuilder.control('');
    this.CtrlDateDepart = this.formBuilder.control('');
    this.CtrlStatut = this.formBuilder.control('');
    this.CtrlStatutAdoption = this.formBuilder.control('');

    this.formStatutAnimal = this.formBuilder.group({
      emplacement: this.CtrlEmplacement,
      animal: this.CtrlAnimal,
      dateArrivee: this.CtrlDateArrivee,
      dateDepart: this.CtrlDateDepart,
      statut: this.CtrlStatut,
      statutAdoption: this.CtrlStatutAdoption,
    });
  }

  private getStatutAnimauxSelonFiltre(): Observable<StatutAnimal[]> {
    if (this.statutFiltre) {
      return this.sAnimalSrv.findByStatut(this.statutFiltre as Statut);
    }
    return this.sAnimalSrv.findAll();
  }

  private reload() {
    this.refresh$.next();
  }

  protected filtrerParStatut(statut: string | null): void {
    this.statutFiltre = statut;
    this.filtreChange$.next();
  }

  protected addOrUpdate() {
    const valeurs = this.formStatutAnimal.getRawValue();

    if (this.editingStatutAnimalId) {
      const sa: StatutAnimal = {
        id: this.editingStatutAnimalId,
        emplacement: valeurs.emplacement,
        animalId: valeurs.animal,
        dateArrivee: valeurs.dateArrivee,
        dateDepart: valeurs.dateDepart,
        statut: valeurs.statut,
        statutAdoption: valeurs.statutAdoption,
      };
      this.sAnimalSrv.update(sa).subscribe(() => this.reload());
    } else {
      const sa: CreateStatutAnimalRequest = {
        emplacementId: valeurs.emplacement,
        animalId: valeurs.animal,
      }
      console.log("SA",sa);
      this.sAnimalSrv.add(sa).subscribe(() => this.reload());
    }

    this.annulerEdition();
  }

  protected edit(sa: StatutAnimal) {
    this.editingStatutAnimalId = sa.id;
    this.CtrlEmplacement.setValue(sa.emplacement);
    this.CtrlAnimal.setValue(sa.animalId);
    this.CtrlDateArrivee.setValue(sa.dateArrivee);
    this.CtrlDateDepart.setValue(sa.dateDepart);
    this.CtrlStatut.setValue(sa.statut);
    this.CtrlStatutAdoption.setValue(sa.statutAdoption);
    this.activerValidationModification();
  }

  protected annulerEdition(): void {
    this.editingStatutAnimalId = 0;
    this.formStatutAnimal.reset();
    this.desactiverValidationModification();
  }

  protected remove(sa: StatutAnimal) {
    this.sAnimalSrv.remove(sa).subscribe(() => this.reload());
  }

  private activerValidationModification(): void {
    this.CtrlDateArrivee.setValidators(Validators.required);
    this.CtrlStatut.setValidators(Validators.required);
    this.CtrlStatutAdoption.setValidators(Validators.required);
    this.CtrlDateArrivee.updateValueAndValidity();
    this.CtrlStatut.updateValueAndValidity();
    this.CtrlStatutAdoption.updateValueAndValidity();
  }

  private desactiverValidationModification(): void {
    this.CtrlDateArrivee.clearValidators();
    this.CtrlStatut.clearValidators();
    this.CtrlStatutAdoption.clearValidators();
    this.CtrlDateArrivee.updateValueAndValidity();
    this.CtrlStatut.updateValueAndValidity();
    this.CtrlStatutAdoption.updateValueAndValidity();
  }

  afficherNomAnimal(n:number) : string {
    return this.animalSrv.findById(n).subscribe(a => a.nomAnimal).toString();
  }
}
