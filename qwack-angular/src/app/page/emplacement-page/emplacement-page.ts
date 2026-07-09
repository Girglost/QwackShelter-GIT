import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Emplacement } from '../../model/emplacement';
import { EmplacementService } from './../../service/emplacement-service';
import { TypeBox } from '../../model/type-box';

@Component({
  selector: 'app-emplacement-page',
  imports: [
    CommonModule,
    ReactiveFormsModule,
  ],
  templateUrl: './emplacement-page.html',
  styleUrl: './emplacement-page.css',
})
export class EmplacementPage implements OnInit{
  private titleService: Title = inject(Title);
  private emplacementSrv : EmplacementService = inject(EmplacementService);

  private refresh$: Subject<void> = new Subject<void>();
  protected emplacements$!: Observable<Emplacement[]>;
  protected typeDeBox$: string[] = Object.values(TypeBox);

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formEmplacement!: FormGroup;
  protected CtrlNbPlace!: FormControl;
  protected CtrlComplet!: FormControl;
  protected CtrlBox!: FormControl;
  protected editingEmplacmeentId: number | undefined=0;

  ngOnInit(): void {
    this.titleService.setTitle("Gestion des Emplacements du Shelter");

    this.emplacements$ = this.refresh$.pipe(
      startWith(0),
      switchMap(() => this.emplacementSrv.findAll())
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

  private reload() {
    this.refresh$.next();
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
