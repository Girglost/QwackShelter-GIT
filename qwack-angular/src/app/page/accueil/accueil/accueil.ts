import { Component } from '@angular/core';
import { Footer } from '../../../component/footer/footer/footer';
import { Header } from '../../../component/header/header/header';

@Component({
  selector: 'app-accueil',
  imports: [Footer,Header],
  templateUrl: './accueil.html',
  styleUrl: './accueil.css',
})
export class Accueil {}
