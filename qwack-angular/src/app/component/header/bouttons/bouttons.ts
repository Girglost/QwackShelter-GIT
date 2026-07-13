import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../service/auth-service';

@Component({
  selector: 'app-bouttons',
  imports: [RouterLink],
  templateUrl: './bouttons.html',
  styleUrl: './bouttons.css',
})
export class Bouttons {

  protected authService = inject(AuthService);

  private router = inject(Router);

  public faireDon() {
    const son = new Audio('assets/son/qwack_jordan.m4a')
    son.play();
    alert("Merci pour le Don de 50 € !")
  }

  public logout() {
    this.authService.resetAuth();
    this.router.navigate(['/accueil']);
  }


}
