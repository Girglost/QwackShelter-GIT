import { Routes } from '@angular/router';
import { Accueil } from './page/accueil/accueil/accueil';
import { Actualites } from './page/actualites/actualites';
import { Contact } from './page/contact/contact';
import { DevenirBenevole } from './page/devenir-benevole/devenir-benevole';
import { EmplacementPage } from './page/emplacement-page/emplacement-page';
import { HistoriqueSantePage } from './page/historique-sante-page/historique-sante-page';
import { NosMissions } from './page/nos-missions/nos-missions';
import { NotFound } from './page/not-found/not-found';
import { NousRejoindre } from './page/nous-rejoindre/nous-rejoindre';
import { StatutAnimalPage } from './page/statut-animal-page/statut-animal-page';
import { SwipeCompagnon } from './page/swipe-compagnon/swipe-compagnon';

import { InscriptionSuccess } from './component/inscription-success/inscription-success';
import { authGuard } from './guard/auth-guard';
import { roleGuardGuard } from './guard/role-guard-guard';
import { APropos } from './page/a-propos/a-propos';
import { AccessDenied } from './page/access-denied/access-denied';
import { AdoptionForm } from './page/adopter/adopter-formulaire/adopter-formulaire';
import { Adopter } from './page/adopter/adopter/adopter';
import { AnimalPage } from './page/animal-page/animal-page';
import { DemandeVisite } from './page/demande-visite/demande-visite';
import { InscriptionPage } from './page/inscription-page/inscription-page';
import { LieuPage } from './page/lieu-page/lieu-page';
import { LoginPage } from './page/login-page/login-page';
import { MesDemandesPage } from './page/mes-demandes/mes-demandes';
import { NosAnimauxPage } from './page/nos-animaux-page/nos-animaux-page';
import { PersonnePage } from './page/personne-page/personne-page';
import { ProfilAnimal } from './page/profil-animal/profil-animal';
import { ProfilPersonnePage } from './page/profil-personne-page/profil-personne-page';
import { MesDemandesPage } from './page/mes-demandes/mes-demandes';
import { MesAdoptions } from './page/mes-adoptions/mes-adoptions';
import { MesVisites } from './page/mes-visites/mes-visites';

export const routes: Routes = [
  { path: 'accueil', component: Accueil },
  { path: 'actualites', component: Actualites },
  { path: 'contact', component: Contact },
  { path: 'nous-rejoindre', component: NousRejoindre },
  { path: 'devenir-benevole', component: DevenirBenevole },
  { path: 'nos-missions', component: NosMissions },
  { path: 'swipe-compagnon', component: SwipeCompagnon },
  { path: 'a-propos', component: APropos },
  { path: 'adopter', component: Adopter },
  { path: 'login', component: LoginPage },
  { path: 'inscription', component: InscriptionPage },
  { path: 'inscription-success', component: InscriptionSuccess },
  { path: 'animaux', component: NosAnimauxPage, },
  { path: 'profil-personne', component: ProfilPersonnePage },
  {path: 'mes-demandes',component:MesDemandesPage},
  {path: 'mes-adoptions',component:MesAdoptions},
  {path: 'mes-visites',component:MesVisites},

  { path: 'animal/:id', component: ProfilAnimal },
  {
    path: 'demande-adoption', component: AdoptionForm, canActivate: [authGuard, roleGuardGuard], data: {
      roles: [
        'VISITEUR', 'BENEVOLE'
      ]
    }
  }, {
    path: 'demande-visite', component: DemandeVisite, canActivate: [authGuard, roleGuardGuard], data: {
      roles: [
        'VISITEUR'
      ]
    }
  },

  {
  path: 'animal/:id',
  loadComponent: () => import('./page/profil-animal/profil-animal').then(m => m.ProfilAnimal),
  },

  // ====== Routes vers le CRUD des classes ======
  {
    path: 'emplacement', component: EmplacementPage, canActivate: [authGuard, roleGuardGuard], data: {
      roles: [
        'PATRON'
      ],
      admin: true
    }
  },
  {
    path: 'sAnimal', component: StatutAnimalPage, canActivate: [authGuard, roleGuardGuard], data: {
      roles: [
        'PATRON'
      ],
      admin: true
    }
  },
  {
    path: 'hSante', component: HistoriqueSantePage, canActivate: [authGuard, roleGuardGuard], data: {
      roles: [
        'PATRON'
      ],
      admin: true
    }
  },
  {
    path: 'personne', component: PersonnePage, canActivate: [authGuard, roleGuardGuard], data: {
      roles: [
        'PATRON'
      ],
      admin: true
    }
  },
  {
    path: 'lieu', component: LieuPage, canActivate: [authGuard, roleGuardGuard], data: {
      roles: [
        'PATRON'
      ],
      admin: true
    }
  },
  {
    path: 'animal', component: AnimalPage, canActivate: [authGuard, roleGuardGuard], data: {
      roles: [
        'PATRON'
      ],
      admin: true
    }
  },
  { path: 'access-denied', component: AccessDenied },
  { path: '**', component: NotFound }, // a mettre a la fin de toutes les routes pour gérer les pages non trouvées

];
