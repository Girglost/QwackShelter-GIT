import { Component } from '@angular/core';
import { DebutAdopter } from '../debut-adopter/debut-adopter';
import { InfoAdopter } from '../info-adopter/info-adopter';
import { FinAdopter } from '../fin-adopter/fin-adopter';
import { TestAdopter } from '../test-adopter/test-adopter';

@Component({
  selector: 'app-adopter',
  imports: [DebutAdopter,InfoAdopter,FinAdopter,TestAdopter],
  templateUrl: './adopter.html',
  styleUrl: './adopter.css',
})
export class Adopter {}
