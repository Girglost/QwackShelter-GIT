import { Component } from '@angular/core';
import { Adoption } from '../adoption/adoption';
import { Hero } from '../hero/hero';
import { Events } from '../events/events';

@Component({
  selector: 'app-accueil',
  imports: [Hero,Events,Adoption],
  templateUrl: './accueil.html',
  styleUrl: './accueil.css',
})
export class Accueil {}
