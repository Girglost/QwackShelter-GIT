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

import { APropos } from './page/a-propos/a-propos';
import { PersonnePage } from './page/personne-page/personne-page';
import { LieuPage } from './page/lieu-page/lieu-page';
import { ProfilAnimal } from './page/profil-animal/profil-animal';
import { Adopter } from './page/adopter/adopter/adopter';
import { AnimalPage } from './page/animal-page/animal-page';
import { NosAnimauxPage } from './page/nos-animaux-page/nos-animaux-page';

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
  { path: 'animaux', component: NosAnimauxPage},

  { path: 'animal/:id', component: ProfilAnimal },

  // ====== Routes vers le CRUD des classes ======
  { path: 'emplacement', component: EmplacementPage },
  { path: 'sAnimal', component: StatutAnimalPage},
  { path: 'hSante', component: HistoriqueSantePage},
  { path: 'personne', component: PersonnePage},
  { path: 'lieu', component: LieuPage},
  { path: 'animal', component: AnimalPage},

  { path: '**', component: NotFound }, // a mettre a la fin de toutes les routes pour gérer les pages non trouvées
];
