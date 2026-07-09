import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { Header } from './component/header/header/header';
import { Footer } from './component/footer/footer/footer';
import { Accueil } from './page/accueil/accueil/accueil';
import { Actualite } from './page/actualites/actualites';
import { NosAnimaux } from './page/nos-animaux/nos-animaux';


export const routes: Routes = [
  {path : "header", component: Header},
  {path : "footer", component: Footer},
  {path : "accueil", component: Accueil},
  {path : "actualites", component: Actualite},
  {path : "nous-animaux", component : NosAnimaux},

];
