import { Component } from '@angular/core';
import { Routes } from '@angular/router';
import { Header } from './component/header/header/header';
import { Footer } from './component/footer/footer/footer';
import { Accueil } from './page/accueil/accueil/accueil';
import { NosAnimaux } from './page/nos-animaux/nos-animaux';
import { Actualites } from './page/actualites/actualites';


export const routes: Routes = [
  {path : "header", component: Header},
  {path : "footer", component: Footer},
  {path : "accueil", component: Accueil},
  {path : "nous-animaux", component : NosAnimaux},
  {path : "actualites", component: Actualites},

];
