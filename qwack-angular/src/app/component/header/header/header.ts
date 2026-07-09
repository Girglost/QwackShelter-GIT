import { Component } from '@angular/core';
import { Blazon } from '../blazon/blazon';
import { Bouttons } from '../bouttons/bouttons';
import { NavigationHeader } from '../navigation-header/navigation-header';

@Component({
  selector: 'app-header',
  imports: [
    NavigationHeader,
    Blazon,
    Bouttons,
  ],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {}
