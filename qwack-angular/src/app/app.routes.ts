import { Routes } from '@angular/router';
import { Footer } from './component/footer/footer/footer';
import { Header } from './component/header/header/header';
import { Accueil } from './page/accueil/accueil/accueil';
import { Actualites } from './page/actualites/actualites';
import { Contact } from './page/contact/contact';
import { EmplacementPage } from './page/emplacement-page/emplacement-page';
import { NousRejoindre } from './page/nous-rejoindre/nous-rejoindre';
import { NotFound } from './page/not-found/not-found';


export const routes: Routes = [
  {path : "accueil", component: Accueil},
  {path : "actualites", component: Actualites},
  {path : "contact", component: Contact},
  {path : "nous-rejoindre", component: NousRejoindre},

  { path: '**', component: NotFound }
  {path : "emplacement", component : EmplacementPage},
];
