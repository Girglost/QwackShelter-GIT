import { Routes } from '@angular/router';
import { Header } from './component/header/header/header';
import { Footer } from './component/footer/footer/footer';
import { Accueil } from './page/accueil/accueil/accueil';
import { Actualite } from './page/actualites/actualites';


export const routes: Routes = [
  {path : "header", component: Header},
  {path : "footer", component: Footer},
  {path : "accueil", component: Accueil},
  {path : "actualites", component: Actualite}

];
