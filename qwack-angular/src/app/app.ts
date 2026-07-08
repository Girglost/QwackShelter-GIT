import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Accueil } from './page/accueil/accueil/accueil';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet,Accueil],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('quack-angular');
}
