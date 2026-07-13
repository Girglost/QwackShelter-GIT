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
import { Role } from '../../enum/role';
import { StatutActivite } from '../../enum/statut-activite';
import { TypeLieu } from '../../enum/type-lieu';
import { Personne } from '../../model/personne';
import { QuackShelter } from '../../model/quack-shelter';
import { PersonneService } from '../../service/personne-service';
import { QuackShelterService } from '../../service/quack-shelter-service';

type TypeFiltre = 'tous' | 'role' | 'quackshelter' | 'statut' | 'admin';

@Component({
  selector: 'app-personne-page',
  imports: [CommonModule, ReactiveFormsModule, FormsModule],
  templateUrl: './personne-page.html',
  styleUrl: './personne-page.css',
})
export class PersonnePage implements OnInit {
  private titleService: Title = inject(Title);
  private personneSrv: PersonneService = inject(PersonneService);
  private quackShelterSrv: QuackShelterService = inject(QuackShelterService);

  private refresh$: Subject<void> = new Subject<void>();
  protected personnes$!: Observable<Personne[]>;

  protected roleValues: string[] = Object.values(Role).filter((r) => r !== Role.PATRON);
  protected roleFiltreValues: string[] = Object.values(Role); // inclut PATRON pour le filtre
  protected statutActiviteValues: string[] = Object.values(StatutActivite);
  protected typeLieuValues: string[] = Object.values(TypeLieu);
  protected quackShelters$!: Observable<QuackShelter[]>;

  // --- Filtre unique actif à la fois ---
  protected filtreType: TypeFiltre = 'tous';
  protected roleFiltre: string | null = null;
  protected quackShelterFiltre: QuackShelter | null = null;
  protected statutFiltre: string | null = null;
  protected adminFiltre: boolean | null = null;
  private filtreChange$ = new Subject<void>();
  protected adminSelectValue: string = '';

  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formPersonne!: FormGroup;

  protected CtrlNom!: FormControl;
  protected CtrlPrenom!: FormControl;
  protected CtrlLogin!: FormControl;
  protected CtrlPassword!: FormControl;
  protected CtrlRole!: FormControl;
  protected CtrlQuackShelter!: FormControl;
  protected CtrlAdmin!: FormControl;
  protected CtrlSalaire!: FormControl;

  protected CtrlTypeLieu!: FormControl;
  protected CtrlNumero!: FormControl;
  protected CtrlVoie!: FormControl;
  protected CtrlVille!: FormControl;
  protected CtrlCP!: FormControl;

  protected editingPersonneId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle('Gestion des Personnes du Shelter');



    this.personnes$ = merge(this.refresh$, this.filtreChange$).pipe(
      startWith(0),
      switchMap(() => this.getPersonnesSelonFiltre()),
    );

    this.quackShelters$ = this.quackShelterSrv.findAll();

    this.CtrlNom = this.formBuilder.control('', Validators.required);
    this.CtrlPrenom = this.formBuilder.control('', Validators.required);
    this.CtrlLogin = this.formBuilder.control('', Validators.required);
    this.CtrlPassword = this.formBuilder.control('', Validators.required);
    this.CtrlRole = this.formBuilder.control('', Validators.required);
    this.CtrlQuackShelter = this.formBuilder.control('', Validators.required);
    this.CtrlAdmin = this.formBuilder.control(false);
    this.CtrlSalaire = this.formBuilder.control('');

    this.CtrlTypeLieu = this.formBuilder.control('', Validators.required);
    this.CtrlNumero = this.formBuilder.control('', Validators.required);
    this.CtrlVoie = this.formBuilder.control('', Validators.required);
    this.CtrlVille = this.formBuilder.control('', Validators.required);
    this.CtrlCP = this.formBuilder.control('', Validators.required);

    this.formPersonne = this.formBuilder.group({
      nom: this.CtrlNom,
      prenom: this.CtrlPrenom,
      login: this.CtrlLogin,
      password: this.CtrlPassword,
      role: this.CtrlRole,
      quackShelterId: this.CtrlQuackShelter,
      admin: this.CtrlAdmin,
      salaire: this.CtrlSalaire,
      habitation: this.formBuilder.group({
        typeLieu: this.CtrlTypeLieu,
        adresse: this.formBuilder.group({
          numero: this.CtrlNumero,
          voie: this.CtrlVoie,
          ville: this.CtrlVille,
          cp: this.CtrlCP
        })

      }),
    });

    // Active/désactive les validateurs admin+salaire selon le rôle choisi
    this.CtrlRole.valueChanges.subscribe((role: string) => {
      if (role === Role.EMPLOYE) {
        this.CtrlSalaire.setValidators([Validators.required, Validators.min(0)]);
      } else {
        this.CtrlSalaire.clearValidators();
        this.CtrlAdmin.setValue(false);
      }
      this.CtrlSalaire.updateValueAndValidity();
    });
  }

  protected get estEmploye(): boolean {
    return this.CtrlRole.value === Role.EMPLOYE;
  }

  private getPersonnesSelonFiltre(): Observable<Personne[]> {
    switch (this.filtreType) {
      case 'role':
        return this.personneSrv.findByRole(this.roleFiltre as Role);
      case 'quackshelter':
        return this.personneSrv.findByQuackShelter(this.quackShelterFiltre!.id);
      case 'statut':
        return this.personneSrv.findByStatutActivite(this.statutFiltre as StatutActivite);
      case 'admin':
        return this.personneSrv.findByAdmin(this.adminFiltre!);
      default:
        return this.personneSrv.findAll();
    }
  }

  private reload() {
    this.refresh$.next();
  }

  private resetFiltres(): void {
    this.roleFiltre = null;
    this.quackShelterFiltre = null;
    this.statutFiltre = null;
    this.adminFiltre = null;
    this.adminSelectValue = '';
  }

  protected filtrerParTous(): void {
    this.filtreType = 'tous';
    this.resetFiltres();
    this.filtreChange$.next();
  }

  protected filtrerParRole(role: string): void {
    this.filtreType = 'role';
    this.resetFiltres();
    this.roleFiltre = role;
    this.filtreChange$.next();
  }

  protected filtrerParQuackShelter(qs: QuackShelter | null): void {
    if (!qs) {
      this.filtrerParTous();
      return;
    }
    this.filtreType = 'quackshelter';
    this.resetFiltres();
    this.quackShelterFiltre = qs;
    this.filtreChange$.next();
  }

  protected filtrerParStatut(statut: string | null): void {
    if (!statut) {
      this.filtrerParTous();
      return;
    }
    this.filtreType = 'statut';
    this.resetFiltres();
    this.statutFiltre = statut;
    this.filtreChange$.next();
  }

  protected filtrerParAdmin(admin: string): void {
    if (admin === '') {
      this.filtrerParTous();
      return;
    }
    this.filtreType = 'admin';
    this.resetFiltres();
    this.adminFiltre = admin === 'true';
    this.adminSelectValue = admin;
    this.filtreChange$.next();
  }

  protected addOrUpdate() {
    const p: Personne = this.formPersonne.getRawValue();

    if (this.editingPersonneId) {
      p.id = this.editingPersonneId;
      this.personneSrv.update(p).subscribe(() => this.reload());
    } else {
      this.personneSrv.add(p).subscribe(() => this.reload());
    }

    this.annulerEdition();
  }

  protected edit(p: Personne) {
    if (p.role === Role.PATRON) {
      return; // pas de modification possible pour un Patron
    }

    this.editingPersonneId = p.id;
    this.CtrlNom.setValue(p.nom);
    this.CtrlPrenom.setValue(p.prenom);
    this.CtrlLogin.setValue(p.login);

    // On ne modifie jamais le password ici
    //this.CtrlPassword.setValue(p.password);
    this.CtrlRole.setValue(p.role);

    this.CtrlTypeLieu.setValue(p.habitation?.typeLieu);
    this.CtrlNumero.setValue(p.habitation?.adresse.numero);
    this.CtrlVoie.setValue(p.habitation?.adresse.voie);
    this.CtrlVille.setValue(p.habitation?.adresse.ville);
    this.CtrlCP.setValue(p.habitation?.adresse.cp);

    //  Permet de select le qs 
    this.CtrlQuackShelter.setValue(p.quackShelterId);

    this.CtrlAdmin.setValue(p.admin);
    this.CtrlSalaire.setValue(p.salaire);

  }

  protected annulerEdition(): void {
    this.editingPersonneId = 0;
    this.formPersonne.reset({ admin: false });
  }

  protected remove(p: Personne) {
    this.personneSrv.remove(p).subscribe(() => this.reload());
  }
}
