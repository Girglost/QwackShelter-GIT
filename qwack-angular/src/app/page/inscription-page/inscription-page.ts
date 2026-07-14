import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Observable } from 'rxjs';
import { TypeLieu } from '../../enum/type-lieu';
import { QuackShelter } from '../../model/quack-shelter';
import { InscriptionService } from '../../service/inscription-service';
import { PersonneService } from '../../service/personne-service';
import { QuackShelterService } from '../../service/quack-shelter-service';
import { loginUniqueValidator } from '../../validator/login-unique-validator';
import { passwordMatchValidator } from '../../validator/password-match-validator';

@Component({
  selector: 'app-inscription-page',
  imports: [ReactiveFormsModule, RouterLink, CommonModule],
  templateUrl: './inscription-page.html',
  styleUrl: './inscription-page.css',
})
export class InscriptionPage implements OnInit {

  private inscriptionService: InscriptionService = inject(InscriptionService);
  private personneService: PersonneService = inject(PersonneService);

  private router: Router = inject(Router);
  private formBuilder: FormBuilder = inject(FormBuilder);
  private quackShelterSrv: QuackShelterService = inject(QuackShelterService);

  protected quackShelters$!: Observable<QuackShelter[]>;

  protected typeLieuDisponible = Object.values(TypeLieu)
    .filter(type => type !== TypeLieu.SHELTER);

  protected formInscription!: FormGroup;
  protected formCtrlNom!: FormControl;
  protected formCtrlPrenom!: FormControl;
  protected formCtrlLogin!: FormControl;
  protected formCtrlPassword!: FormControl;
  protected formCtrlPasswordConfirm!: FormControl;
  protected formCtrlRole!: FormControl;
  protected formCtrlQuackShelter!: FormControl;

  protected formCtrlTypeLieu!: FormControl;
  protected formCtrlNumero!: FormControl;
  protected formCtrlVoie!: FormControl;
  protected formCtrlVille!: FormControl;
  protected formCtrlCP!: FormControl;

  protected errorInscription = signal(false);


  ngOnInit(): void {
    this.quackShelters$ = this.quackShelterSrv.findAll();


    this.formCtrlNom = this.formBuilder.control('', Validators.required);
    this.formCtrlPrenom = this.formBuilder.control('', Validators.required);
    this.formCtrlLogin = this.formBuilder.control('', Validators.required, [
      loginUniqueValidator(this.personneService)
    ]);
    this.formCtrlPassword = this.formBuilder.control('', [Validators.required, Validators.minLength(4)]);
    this.formCtrlPasswordConfirm = this.formBuilder.control('', [Validators.required, Validators.minLength(4)]);

    this.formCtrlRole = this.formBuilder.control('', Validators.required);
    this.formCtrlQuackShelter = this.formBuilder.control('', Validators.required);

    this.formCtrlTypeLieu = this.formBuilder.control('', Validators.required);
    this.formCtrlNumero = this.formBuilder.control('', Validators.required);
    this.formCtrlVoie = this.formBuilder.control('', Validators.required);
    this.formCtrlVille = this.formBuilder.control('', Validators.required);
    this.formCtrlCP = this.formBuilder.control('', Validators.required);

    this.formInscription = this.formBuilder.group({
      nom: this.formCtrlNom,
      prenom: this.formCtrlPrenom,
      login: this.formCtrlLogin,
      password: this.formCtrlPassword,
      passwordConfirm: this.formCtrlPasswordConfirm,
      role: this.formCtrlRole,
      quackShelterId: this.formCtrlQuackShelter,
      habitation: this.formBuilder.group({
        typeLieu: this.formCtrlTypeLieu,
        adresse: this.formBuilder.group({
          numero: this.formCtrlNumero,
          voie: this.formCtrlVoie,
          ville: this.formCtrlVille,
          cp: this.formCtrlCP
        })

      }),
    }, {
      validators: [
        passwordMatchValidator(
          'password',
          'passwordConfirm'
        )
      ]
    });


  }

  public async inscription() {
    try {
      await this.inscriptionService.inscription(this.formInscription.getRawValue());

      this.router.navigate(['/login']);
    }

    // Si l'inscription n'a pas pu se faire, affichage de l'erreur sur le template
    catch {
      this.errorInscription.set(true);
    }
  }


}
