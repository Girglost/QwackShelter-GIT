import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Role } from '../../enum/role';
import { RouterLink } from "@angular/router";
import { CurrentUser } from '../../model/current-user';
import { AuthService } from '../../service/auth-service';
import { Personne } from '../../model/personne';
import { PersonneService } from '../../service/personne-service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-employe-page',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './profil-personne-page.html',
  styleUrls: ['./profil-personne-page.css']
})
export class ProfilPersonnePage implements OnInit {

  //private personne$!: Observable<Personne>;

  personne?:Personne;

  constructor(
    private authService: AuthService,
    private personneService: PersonneService
  ) {}

  get currentUser() {
    return this.authService.currentUser();
  }

ngOnInit(): void {

  this.authService.loadCurrentUser()
    .subscribe(user => {

      console.log("USER DANS COMPOSANT :", user);

      this.personneService.findByLogin(user.login)
        .subscribe(personne => {

          console.log(personne);

          this.personne = personne;

        });

    });
}

  get roleLabel(): string {
    switch (this.personne?.role) {
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

    switch (this.personne?.role) {
      case Role.BENEVOLE:
        return this.personne.dateEngagement;

      case Role.EMPLOYE:
        return this.personne.dateEmbauche;

      case Role.VISITEUR:
        return this.personne.dateInscription;

      default:
        return undefined;
    }
  }

}

