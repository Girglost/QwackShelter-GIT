import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { Router, RouterLink } from "@angular/router";
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
  selector: 'app-mes-demandes-page',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './mes-demandes.html',
  styleUrls: ['./mes-demandes.css']
})
export class MesDemandesPage implements OnInit {

  personne = signal<Personne | null>(null);

  constructor(
    private authService: AuthService,
    private personneService: PersonneService,
    private router: Router
  ) { }

  get currentUser() {
    return this.authService.currentUser();
  }

  ngOnInit(): void {
    this.personneService.getMonProfil()
      .subscribe(personne => {
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

  get isAdmin(): boolean {
    return !!(this.personne() as any)?.admin;
  }

  get isVisiteur(): boolean {
    return this.personne()?.role === Role.VISITEUR;
  }

  get isBenevole(): boolean {
    return this.personne()?.role === Role.BENEVOLE;
  }

  get isEmploye(): boolean {
    return this.personne()?.role === Role.EMPLOYE;
  }

  get isPatron(): boolean {
    return this.personne()?.role === Role.PATRON;
  }

  get hasPlanning(): boolean {
    return this.isBenevole || this.isEmploye || this.isPatron;
  }

  get hasStats(): boolean {
    return this.isEmploye || this.isAdmin;
  }

  get menuItems(): MenuItem[] {
    const role = this.personne()?.role;

    const items: MenuItem[] = [
      { icon: 'fa-regular fa-user', label: 'Mon profil' },
      { icon: 'fa-regular fa-envelope', label: 'Mes demandes', link: '/mes-demandes' },
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
          { icon: 'fa-regular fa-calendar', label: 'Mon planning', link: '/planning' },
          { icon: 'fa-solid fa-list-check', label: 'Mes tâches' },
        );
        break;

      case Role.EMPLOYE:
        items.push(
          { icon: 'fa-regular fa-calendar', label: 'Mon planning', link: '/planning' },
          { icon: 'fa-solid fa-list-check', label: 'Mes tâches' },
          { icon: 'fa-solid fa-kit-medical', label: 'Soins des animaux' },
          { icon: 'fa-solid fa-folder-open', label: 'Gestion des demandes' },
        );
        break;

      case Role.PATRON:
        items.push(
          { icon: 'fa-regular fa-calendar', label: 'Mon planning', link: '/planning' },
          { icon: 'fa-solid fa-list-check', label: 'Mes tâches', link: '/taches' },
        );
        break;
    }

    if (this.isAdmin) {
      items.push(
        { icon: 'fa-solid fa-users-gear', label: 'Gestion des personnes', link: '/personne' },
        { icon: 'fa-solid fa-dog', label: 'Gestion des animaux', link: '/animal' },
        { icon: 'fa-solid fa-dog', label: 'Gestion des refuges', link: '/refuge' }
      );
    }

    return items;
  }

  logout(): void {
    this.authService.resetAuth();
    this.router.navigate(['/accueil']);
  }

}
