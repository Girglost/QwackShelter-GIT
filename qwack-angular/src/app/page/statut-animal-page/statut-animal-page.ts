import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { merge, Observable, startWith, Subject, switchMap } from 'rxjs';
import { StatutAnimal } from '../../model/statut-animal';
import { StatutAnimalService } from '../../service/statut-animal-service';
import { Statut } from '../../model/statut';
import { StatutValidation } from '../../model/statut-validation';
import { Emplacement } from '../../model/emplacement';
import { Animal } from '../../model/animal';
import { EmplacementService } from '../../service/emplacement-service';
import { AnimalService } from '../../service/animal-service';

@Component({
  selector: 'app-statut-animal-page',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
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

  // --- Filtre par statut (seul endpoint disponible côté backend) ---
  protected statutFiltre: string | null = null;
  private filtreChange$ = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formStatutAnimal!: FormGroup;
  protected CtrlDateArrivee!: FormControl;
  protected CtrlDateDepart!: FormControl;
  protected CtrlStatut!: FormControl;
  protected CtrlStatutAdoption!: FormControl;
  protected CtrlEmplacement!: FormControl;
  protected CtrlAnimal!: FormControl;
  protected editingStatutAnimalId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle("Gestion des StatutAnimals du Shelter");

    this.sAnimals$ = merge(this.refresh$, this.filtreChange$).pipe(
      startWith(0),
      switchMap(() => this.getStatutAnimauxSelonFiltre())
    );

    this.emplacements$ = this.emplacementSrv.findAll();
    this.animaux$ = this.animalSrv.findAll();

    this.CtrlDateArrivee = this.formBuilder.control('', Validators.required);
    this.CtrlDateDepart = this.formBuilder.control('');
    this.CtrlStatut = this.formBuilder.control('', Validators.required);
    this.CtrlStatutAdoption = this.formBuilder.control('');
    this.CtrlEmplacement = this.formBuilder.control('', Validators.required);
    this.CtrlAnimal = this.formBuilder.control('', Validators.required);

    this.formStatutAnimal = this.formBuilder.group({
      dateArrivee: this.CtrlDateArrivee,
      dateDepart: this.CtrlDateDepart,
      statut: this.CtrlStatut,
      statutAdoption: this.CtrlStatutAdoption,
      emplacement: this.CtrlEmplacement,
      animal: this.CtrlAnimal,
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
    const sa: StatutAnimal = this.formStatutAnimal.getRawValue();

    if (this.editingStatutAnimalId) {
      sa.id = this.editingStatutAnimalId;
      this.sAnimalSrv.update(sa).subscribe(() => this.reload());
    } else {
      this.sAnimalSrv.add(sa).subscribe(() => this.reload());
    }

    this.formStatutAnimal.reset();
    this.editingStatutAnimalId = 0;
  }

  protected edit(sa: StatutAnimal) {
    this.editingStatutAnimalId = sa.id;
    this.CtrlDateArrivee.setValue(sa.dateArrivee);
    this.CtrlDateDepart.setValue(sa.dateDepart);
    this.CtrlStatut.setValue(sa.statut);
    this.CtrlStatutAdoption.setValue(sa.statutAdoption);
    this.CtrlEmplacement.setValue(sa.emplacement);
    this.CtrlAnimal.setValue(sa.animal);
  }

  protected remove(sa: StatutAnimal) {
    this.sAnimalSrv.remove(sa).subscribe(() => this.reload());
  }
}
