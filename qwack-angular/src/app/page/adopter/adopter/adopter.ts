import { Component } from '@angular/core';
import { DebutAdopter } from '../debut-adopter/debut-adopter';
import { InfoAdopter } from '../info-adopter/info-adopter';
import { FinAdopter } from '../fin-adopter/fin-adopter';

@Component({
  selector: 'app-adopter',
  imports: [DebutAdopter,InfoAdopter,FinAdopter],
  templateUrl: './adopter.html',
  styleUrl: './adopter.css',
})
export class Adopter {}
