import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { RouterLink } from "@angular/router";
import { Role } from '../../enum/role';
import { Personne } from '../../model/personne';
import { AuthService } from '../../service/auth-service';
import { PersonneService } from '../../service/personne-service';

interface MenuItem {
  icon: string;
  label: string;
  link?: string;
}

@Component({
  selector: 'app-employe-page',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './profil-personne-page.html',
  styleUrls: ['./profil-personne-page.css']
})
export class ProfilPersonnePage implements OnInit {

  personne = signal<Personne | null>(null);

  constructor(
    private authService: AuthService,
    private personneService: PersonneService
  ) { }

  get currentUser() {
    return this.authService.currentUser();
  }

  ngOnInit(): void {
<<<<<<< Updated upstream

    this.personneService.getMonProfil()
      .subscribe(personne => {

        console.log("PROFIL :", personne);

        this.personne.set(personne);

      });
=======
    const user = this.authService.currentUser();

    if (user) {
      this.personneService.findByLogin(user.login)
        .subscribe(personne => {
          console.log("PERSONNE COMPLETE :", personne);
          this.personne.set(personne);
        });
    }
>>>>>>> Stashed changes
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
      default:
        return undefined;
    }
  }

  /**
   * ⚠️ À adapter selon le nom réel du champ dans ton modèle Personne
   * (ex: personne.admin, personne.estAdmin, personne.isAdmin...)
   */
  get isAdmin(): boolean {
    return !!(this.personne() as any)?.admin;
  }

get menuItems(): MenuItem[] {
    const role = this.personne()?.role;

    // Items communs à tous les rôles
    const items: MenuItem[] = [
      { icon: 'fa-regular fa-user', label: 'Mon profil' },
      { icon: 'fa-regular fa-envelope', label: 'Mes demandes' },
      { icon: 'fa-solid fa-paw', label: 'Mes adoptions' },
      { icon: 'fa-regular fa-calendar-check', label: 'Mes visites' },
      { icon: 'fa-solid fa-hand-holding-heart', label: 'Mes dons' },
    ];

    switch (role) {
      case Role.VISITEUR:
        items.push(
          { icon: 'fa-solid fa-heart', label: 'Mes parrainages' }
        );
        break;

      case Role.BENEVOLE:
        items.push(
          { icon: 'fa-solid fa-heart', label: 'Mes parrainages' },
          { icon: 'fa-regular fa-calendar', label: 'Mon planning' },
          { icon: 'fa-solid fa-list-check', label: 'Mes tâches' },
        );
        break;

      case Role.EMPLOYE:
        items.push(
          { icon: 'fa-regular fa-calendar', label: 'Mon planning' },
          { icon: 'fa-solid fa-list-check', label: 'Mes tâches' },
          { icon: 'fa-solid fa-kit-medical', label: 'Soins des animaux' },
          { icon: 'fa-solid fa-folder-open', label: 'Gestion des demandes' },
        );
        break;
    }

    // Ajout des items admin, quel que soit le rôle
    if (this.isAdmin) {
      items.push(
        { icon: 'fa-solid fa-users-gear', label: 'Gestion des personnes', link: '/gestion-benevole' },
        { icon: 'fa-solid fa-dog', label: 'Gestion des animaux', link: '/animal' },
      );
    }

    return items;
  }

}
