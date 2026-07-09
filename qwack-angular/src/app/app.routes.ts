import { Routes } from '@angular/router';
import { Accueil } from './page/accueil/accueil/accueil';
import { Actualites } from './page/actualites/actualites';
import { Contact } from './page/contact/contact';
import { EmplacementPage } from './page/emplacement-page/emplacement-page';
import { NousRejoindre } from './page/nous-rejoindre/nous-rejoindre';
import { NotFound } from './page/not-found/not-found';

export const routes: Routes = [
  { path: 'accueil', component: Accueil },
  { path: 'actualites', component: Actualites },
  { path: 'contact', component: Contact },
  { path: 'nous-rejoindre', component: NousRejoindre },

  { path: 'emplacement', component: EmplacementPage },

  { path: '**', component: NotFound }, // a mettre a la fin de toutes les routes pour gérer les pages non trouvées
];
