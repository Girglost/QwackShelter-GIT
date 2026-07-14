import { Component, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Footer } from './component/footer/footer/footer';
import { Header } from './component/header/header/header';
import { AuthService } from './service/auth-service';


@Component({
  selector: 'app-root',
  imports: [
    RouterOutlet,
    Header,
    Footer,
  ],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('quack-angular');

  private authService: AuthService = inject(AuthService);

  ngOnInit() {
    this.authService.initialize();
  }
}
