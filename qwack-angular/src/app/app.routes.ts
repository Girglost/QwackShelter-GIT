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

import { authGuard } from './guard/auth-guard';
import { APropos } from './page/a-propos/a-propos';
import { Adopter } from './page/adopter/adopter/adopter';
import { AnimalPage } from './page/animal-page/animal-page';
import { LieuPage } from './page/lieu-page/lieu-page';
import { LoginPage } from './page/login-page/login-page';
import { PersonnePage } from './page/personne-page/personne-page';
import { ProfilAnimal } from './page/profil-animal/profil-animal';

export const routes: Routes = [
  { path: 'accueil', component: Accueil },
  { path: 'actualites', component: Actualites },
  { path: 'contact', component: Contact },
  { path: 'nous-rejoindre', component: NousRejoindre },
  { path: 'devenir-benevole', component: DevenirBenevole },
  { path: 'nos-missions', component: NosMissions },
  { path: 'swipe-compagnon', component: SwipeCompagnon },
  { path: 'a-propos', component: APropos },
  { path: 'adopter', component: Adopter, canActivate: [authGuard] },
  { path: 'login', component: LoginPage },

  { path: 'animal/:id', component: ProfilAnimal },

  // ====== Routes vers le CRUD des classes ======
  { path: 'emplacement', component: EmplacementPage, canActivate: [authGuard] },
  { path: 'sAnimal', component: StatutAnimalPage, canActivate: [authGuard] },
  { path: 'hSante', component: HistoriqueSantePage, canActivate: [authGuard] },
  { path: 'personne', component: PersonnePage, canActivate: [authGuard] },
  { path: 'lieu', component: LieuPage, canActivate: [authGuard] },
  { path: 'animal', component: AnimalPage, canActivate: [authGuard] },

  { path: '**', component: NotFound }, // a mettre a la fin de toutes les routes pour gérer les pages non trouvées
];
