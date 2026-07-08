import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { BasFooter } from '../bas-footer/bas-footer';
import { NavigationFooter } from '../navigation-footer/navigation-footer';
import { NewsletterFooter } from '../newsletter-footer/newsletter-footer';
import { InfoRefugeFooter } from '../info-refuge-footer/info-refuge-footer';

@Component({
  selector: 'app-footer',
  imports: [RouterLink,RouterOutlet,BasFooter,NavigationFooter,NewsletterFooter,InfoRefugeFooter],
  templateUrl: './footer.html',
  styleUrl: './footer.css',
})
export class Footer {}
