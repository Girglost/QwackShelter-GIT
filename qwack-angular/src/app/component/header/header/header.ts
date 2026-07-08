import { Component } from '@angular/core';
import { Blazon } from '../blazon/blazon';
import { Bouttons } from '../bouttons/bouttons';
import { NavigationHeader } from '../navigation-header/navigation-header';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [
    NavigationHeader,
    Blazon,
    Bouttons,
    RouterLink
  ],
  templateUrl: './header.html',
  styleUrl: './header.css',
})
export class Header {}
