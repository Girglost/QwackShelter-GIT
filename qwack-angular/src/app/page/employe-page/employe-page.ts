import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Personne } from '../../model/personne';
import { Role } from '../../enum/role';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-employe-page',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './employe-page.html',
  styleUrls: ['./employe-page.css']
})
export class EmployePage {

personne?: Personne;

get roleLabel(): string {
    switch (this.personne?.role) {
      case Role.BENEVOLE:
        return 'Bénévole';

      case Role.EMPLOYE:
        return 'Employé';

      case Role.PATRON:
        return 'Patron';

      default:
        return '';
    }
  }

get dateRole(): Date | undefined {
  switch (this.personne?.role) {
    case Role.BENEVOLE:
      return this.personne.dateEngagement;

    case Role.EMPLOYE:
      return this.personne.dateEmbauche;

    default:
      return undefined;
  }
}


}

