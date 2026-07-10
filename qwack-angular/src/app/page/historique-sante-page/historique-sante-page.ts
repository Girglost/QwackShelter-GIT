import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { merge, Observable, startWith, Subject, switchMap } from 'rxjs';
import { HistoriqueSante } from '../../model/historique-sante';
import { HistoriqueSanteService } from '../../service/historique-sante-service';
import { Cause } from '../../enum/cause';
import { Animal } from '../../model/animal';
import { AnimalService } from '../../service/animal-service';

@Component({
  selector: 'app-historique-sante-page',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './historique-sante-page.html',
  styleUrl: './historique-sante-page.css',
})
export class HistoriqueSantePage implements OnInit {
  private titleService: Title = inject(Title);
  private hsSrv: HistoriqueSanteService = inject(HistoriqueSanteService);
  private animalSrv: AnimalService = inject(AnimalService);

  private refresh$: Subject<void> = new Subject<void>();
  protected historiques$!: Observable<HistoriqueSante[]>;

  protected causeValues: string[] = Object.values(Cause);
  protected animaux$!: Observable<Animal[]>;

  // --- Filtres : un seul actif à la fois ---
  protected causeFiltre: string | null = null;
  protected animalFiltre: Animal | null = null;
  private filtreChange$ = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formHistorique!: FormGroup;
  protected CtrlCause!: FormControl;
  protected CtrlDate!: FormControl;
  protected CtrlHeure!: FormControl;
  protected CtrlPoids!: FormControl;
  protected CtrlCommentaire!: FormControl;
  protected CtrlAnimal!: FormControl;

  protected editingHistoriqueId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle("Gestion de l'Historique de Santé du Shelter");

    this.historiques$ = merge(this.refresh$, this.filtreChange$).pipe(
      startWith(0),
      switchMap(() => this.getHistoriquesSelonFiltre())
    );

    this.animaux$ = this.animalSrv.findAll();

    this.CtrlCause = this.formBuilder.control('', Validators.required);
    this.CtrlDate = this.formBuilder.control('', Validators.required);
    this.CtrlHeure = this.formBuilder.control('', Validators.required);
    this.CtrlPoids = this.formBuilder.control('', [Validators.required, Validators.min(0)]);
    this.CtrlCommentaire = this.formBuilder.control('');
    this.CtrlAnimal = this.formBuilder.control('', Validators.required);

    this.formHistorique = this.formBuilder.group({
      cause: this.CtrlCause,
      date: this.CtrlDate,
      heure: this.CtrlHeure,
      poids: this.CtrlPoids,
      commentaire: this.CtrlCommentaire,
      animal: this.CtrlAnimal,
    });
  }

  private getHistoriquesSelonFiltre(): Observable<HistoriqueSante[]> {
    if (this.animalFiltre) {
      return this.hsSrv.findByAnimal(this.animalFiltre.id);
    }
    if (this.causeFiltre) {
      return this.hsSrv.findByCause(this.causeFiltre as Cause);
    }
    return this.hsSrv.findAll();
  }

  private reload() {
    this.refresh$.next();
  }

  protected filtrerParCause(cause: string | null): void {
    this.causeFiltre = cause;
    this.animalFiltre = null; // un seul filtre à la fois
    this.filtreChange$.next();
  }

  protected filtrerParAnimal(animal: Animal | null): void {
    this.animalFiltre = animal;
    this.causeFiltre = null; // un seul filtre à la fois
    this.filtreChange$.next();
  }

  protected addOrUpdate() {
    const h: HistoriqueSante = this.formHistorique.getRawValue();

    if (this.editingHistoriqueId) {
      h.id = this.editingHistoriqueId;
      this.hsSrv.update(h).subscribe(() => this.reload());
    } else {
      this.hsSrv.add(h).subscribe(() => this.reload());
    }

    this.annulerEdition();
  }

  protected edit(h: HistoriqueSante) {
    this.editingHistoriqueId = h.id;
    this.CtrlCause.setValue(h.cause);
    this.CtrlDate.setValue(h.date);
    this.CtrlHeure.setValue(h.heure);
    this.CtrlPoids.setValue(h.poids);
    this.CtrlCommentaire.setValue(h.commentaire);
    this.CtrlAnimal.setValue(h.animal);
  }

  protected annulerEdition(): void {
    this.editingHistoriqueId = 0;
    this.formHistorique.reset();
  }

  protected remove(h: HistoriqueSante) {
    this.hsSrv.remove(h).subscribe(() => this.reload());
  }
}
