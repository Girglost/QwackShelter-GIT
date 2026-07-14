import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { RouterLink } from "@angular/router";
import { Role } from '../../enum/role';
import { Personne } from '../../model/personne';
import { AuthService } from '../../service/auth-service';
import { PersonneService } from '../../service/personne-service';

@Component({
  selector: 'app-employe-page',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './profil-personne-page.html',
  styleUrls: ['./profil-personne-page.css']
})
export class ProfilPersonnePage implements OnInit {

  //private personne$!: Observable<Personne>;

  // personne?: Personne;
  personne = signal<Personne | null>(null);

  constructor(
    private authService: AuthService,
    private personneService: PersonneService
  ) { }

  get currentUser() {
    return this.authService.currentUser();
  }

  ngOnInit(): void {

    this.personneService.getMonProfil()
      .subscribe(personne => {

        console.log("PROFIL :", personne);

        this.personne.set(personne);

      });
  }

  get roleLabel(): string {
    switch (this.personne()?.role) {
      case Role.BENEVOLE:
        return 'Bénévole';

      case Role.EMPLOYE:
        return 'Employé';

      case Role.PATRON:
        return 'Patron';

      case Role.VISITEUR:
        return 'Visiteur';

      default:
        return '';
    }
  }

  get dateRole(): Date | undefined {
    if (!this.personne) return undefined;

    switch (this.personne()?.role) {
      case Role.BENEVOLE:
        return this.personne()?.dateEngagement;

      case Role.EMPLOYE:
        return this.personne()?.dateEmbauche;

      case Role.VISITEUR:
        return this.personne()?.dateInscription;
      case Role.PATRON:
        return this.personne()?.dateEmbauche;

      default:
        return undefined;
    }
  }

}

