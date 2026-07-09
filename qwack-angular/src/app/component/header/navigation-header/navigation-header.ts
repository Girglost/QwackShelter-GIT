import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-navigation-header',
  imports: [RouterLink, RouterLinkActive],
  templateUrl: './navigation-header.html',
  styleUrl: './navigation-header.css',
})
export class NavigationHeader {

}
