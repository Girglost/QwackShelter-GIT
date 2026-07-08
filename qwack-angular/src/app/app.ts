import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Header } from './component/header/header/header';
import { Footer } from './component/footer/footer/footer';
import { Actualite } from './page/actualites/actualites';
import { Accueil } from './page/accueil/accueil/accueil';


@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    Accueil,
    Header,
    Actualite,
    Footer
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('quack-angular');
}
