import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { merge, Observable, startWith, Subject, switchMap } from 'rxjs';
import { Lieu } from '../../model/lieu';
import { LieuService } from '../../service/lieu-service';
import { TypeLieu } from '../../enum/type-lieu';

@Component({
  selector: 'app-lieu-page',
  imports: [
    CommonModule,
    ReactiveFormsModule,
    FormsModule
  ],
  templateUrl: './lieu-page.html',
  styleUrl: './lieu-page.css',
})
export class LieuPage implements OnInit {
  private titleService: Title = inject(Title);
  private lieuSrv: LieuService = inject(LieuService);

  private refresh$: Subject<void> = new Subject<void>();
  protected lieux$!: Observable<Lieu[]>;

  protected typeLieuValues: string[] = Object.values(TypeLieu);

  // --- Filtre par typeLieu ---
  protected typeLieuFiltre: string | null = null;
  private filtreChange$ = new Subject<void>();

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formLieu!: FormGroup;

  protected CtrlTypeLieu!: FormControl;
  protected CtrlNumero!: FormControl;
  protected CtrlVoie!: FormControl;
  protected CtrlVille!: FormControl;
  protected CtrlCodePostal!: FormControl;

  protected editingLieuId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle("Gestion des Lieux du Shelter");

    this.lieux$ = merge(this.refresh$, this.filtreChange$).pipe(
      startWith(0),
      switchMap(() => this.getLieuxSelonFiltre())
    );

    this.CtrlTypeLieu = this.formBuilder.control('', Validators.required);
    this.CtrlNumero = this.formBuilder.control('', Validators.required);
    this.CtrlVoie = this.formBuilder.control('', Validators.required);
    this.CtrlVille = this.formBuilder.control('', Validators.required);
    this.CtrlCodePostal = this.formBuilder.control('', Validators.required);

    this.formLieu = this.formBuilder.group({
      typeLieu: this.CtrlTypeLieu,
      adresse: this.formBuilder.group({
        numero: this.CtrlNumero,
        voie: this.CtrlVoie,
        ville: this.CtrlVille,
        codePostal: this.CtrlCodePostal,
      })
    });
  }

  private getLieuxSelonFiltre(): Observable<Lieu[]> {
    if (this.typeLieuFiltre) {
      return this.lieuSrv.findByTypeLieu(this.typeLieuFiltre as TypeLieu);
    }
    return this.lieuSrv.findAll();
  }

  private reload() {
    this.refresh$.next();
  }

  protected filtrerParTypeLieu(type: string | null): void {
    this.typeLieuFiltre = type;
    this.filtreChange$.next();
  }

  protected addOrUpdate() {
    const l: Lieu = this.formLieu.getRawValue();

    if (this.editingLieuId) {
      l.id = this.editingLieuId;
      this.lieuSrv.update(l).subscribe(() => this.reload());
    } else {
      this.lieuSrv.add(l).subscribe(() => this.reload());
    }

    this.annulerEdition();
  }

  protected edit(l: Lieu) {
    this.editingLieuId = l.id;
    this.CtrlTypeLieu.setValue(l.typeLieu);
    this.CtrlNumero.setValue(l.adresse?.numero);
    this.CtrlVoie.setValue(l.adresse?.voie);
    this.CtrlVille.setValue(l.adresse?.ville);
    this.CtrlCodePostal.setValue(l.adresse?.codePostal);
  }

  protected annulerEdition(): void {
    this.editingLieuId = 0;
    this.formLieu.reset();
  }

  protected remove(l: Lieu) {
    this.lieuSrv.remove(l).subscribe(() => this.reload());
  }
}
