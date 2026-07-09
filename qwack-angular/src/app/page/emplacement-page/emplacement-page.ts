import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { BehaviorSubject, combineLatest, Observable, startWith, Subject, switchMap, map, merge } from 'rxjs';
import { Emplacement } from '../../model/emplacement';
import { EmplacementService } from './../../service/emplacement-service';
import { TypeBox } from '../../model/type-box';

type FiltreStatut = 'tous' | 'complet' | 'disponible';

@Component({
  selector: 'app-emplacement-page',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './emplacement-page.html',
  styleUrl: './emplacement-page.css',
})
export class EmplacementPage implements OnInit{
  private titleService: Title = inject(Title);
  private emplacementSrv : EmplacementService = inject(EmplacementService);

  private refresh$: Subject<void> = new Subject<void>();
  protected emplacements$!: Observable<Emplacement[]>;
  protected emplacementsFiltres$!: Observable<Emplacement[]>;
  protected typeDeBox$: string[] = Object.values(TypeBox);

  // --- Filtres ---
  protected filtreActif: FiltreStatut = 'tous';
  protected typeSelectionne: string | null = null;
  private filtreStatut$ = new BehaviorSubject<FiltreStatut>('tous');
  private filtreType$ = new BehaviorSubject<string | null>(null);

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formEmplacement!: FormGroup;
  protected CtrlNbPlace!: FormControl;
  protected CtrlComplet!: FormControl;
  protected CtrlBox!: FormControl;
  protected editingEmplacmeentId: number | undefined=0;

  private filtreChange$ = new Subject<void>();

ngOnInit(): void {
  this.titleService.setTitle("Gestion des Emplacements du Shelter");

  this.emplacements$ = merge(
    this.refresh$,
    this.filtreChange$
  ).pipe(
    startWith(0),
    switchMap(() => this.getEmplacementsSelonFiltre())
  );

  this.CtrlBox = this.formBuilder.control('', Validators.required);
  this.CtrlNbPlace = this.formBuilder.control('1', [Validators.required, Validators.min(1)]);
  this.CtrlComplet = this.formBuilder.control('');

  this.formEmplacement = this.formBuilder.group({
    complet: this.CtrlComplet,
    box: this.CtrlBox,
    nbPlace: this.CtrlNbPlace
  });
}

private getEmplacementsSelonFiltre(): Observable<Emplacement[]> {
  if (this.typeSelectionne) {
    return this.emplacementSrv.findByBox(this.typeSelectionne as TypeBox);
  }

  switch (this.filtreActif) {
    case 'complet':
      return this.emplacementSrv.findComplet();
    case 'disponible':
      return this.emplacementSrv.findDispo();
    default:
      return this.emplacementSrv.findAll();
  }
}

private reload() {
  this.refresh$.next();
}

protected filtrerPar(statut: FiltreStatut): void {
  this.filtreActif = statut;
  this.typeSelectionne = null; // un seul filtre à la fois
  this.filtreChange$.next();
}

protected filtrerParType(type: TypeBox | null): void {
  this.typeSelectionne = type;
  this.filtreActif = 'tous'; // annule le filtre statut visuellement
  this.filtreChange$.next();
}

  protected addOrUpdate(){
    const emp: Emplacement = this.formEmplacement.getRawValue();

    if(this.editingEmplacmeentId){
      emp.id = this.editingEmplacmeentId;
      this.emplacementSrv.update(emp).subscribe(() => this.reload());
    }
    else{
      this.emplacementSrv.add(emp).subscribe(()=> this.reload())
    }

    this.formEmplacement.reset({
      box: ""
    });
    this.editingEmplacmeentId = 0;
  }

  protected edit(emp: Emplacement){
    this.editingEmplacmeentId = emp.id;
    this.CtrlBox.setValue(emp.box);
    this.CtrlComplet.setValue(emp.complet);
    this.CtrlNbPlace.setValue(emp.nbPlace);
  }

  protected remove(emp: Emplacement){
    this.emplacementSrv.remove(emp).subscribe(()=>this.reload());
  }
}
