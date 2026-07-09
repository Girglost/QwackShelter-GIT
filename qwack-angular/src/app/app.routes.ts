import { Routes } from '@angular/router';
import { Accueil } from './page/accueil/accueil/accueil';
import { Actualites } from './page/actualites/actualites';
import { Contact } from './page/contact/contact';
import { EmplacementPage } from './page/emplacement-page/emplacement-page';
import { NousRejoindre } from './page/nous-rejoindre/nous-rejoindre';
import { NotFound } from './page/not-found/not-found';
import { DevenirBenevole } from './page/devenir-benevole/devenir-benevole';
import { NosMissions } from './page/nos-missions/nos-missions';
import { SwipeCompagnon } from './page/swipe-compagnon/swipe-compagnon';
import { StatutAnimalPage } from './page/statut-animal-page/statut-animal-page';
import { HistoriqueSantePage } from './page/historique-sante-page/historique-sante-page';

export const routes: Routes = [
  { path: 'accueil', component: Accueil },
  { path: 'actualites', component: Actualites },
  { path: 'contact', component: Contact },
  { path: 'nous-rejoindre', component: NousRejoindre },
  { path: 'devenir-benevole', component: DevenirBenevole },
  { path: 'nos-missions', component: NosMissions },
  { path: 'swipe-compagnon', component: SwipeCompagnon },

  // ====== Routes vers les CRUD des classes ======
  { path: 'emplacement', component: EmplacementPage },
  { path: 'sAnimal', component: StatutAnimalPage},
  { path: 'hSante', component: HistoriqueSantePage},


  { path: '**', component: NotFound }, // a mettre a la fin de toutes les routes pour gérer les pages non trouvées
];
