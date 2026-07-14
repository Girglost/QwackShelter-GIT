import { Component, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-inscription-success',
  imports: [RouterLink],
  templateUrl: './inscription-success.html',
  styleUrl: './inscription-success.css',
})
export class InscriptionSuccess {

  private router: Router = inject(Router)
}
