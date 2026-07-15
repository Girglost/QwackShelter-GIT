import { Component } from '@angular/core';
import { RouterLink } from "@angular/router";

interface EvenementAVenir {
  date: string;
  titre: string;
}

@Component({
  selector: 'app-events',
  imports: [RouterLink],
  templateUrl: './events.html',
  styleUrl: './events.css',
})
export class Events {
  evenementsAVenir: EvenementAVenir[] = [
    { date: '12 - 06 - 2025', titre: 'Journée portes ouvertes' },
    { date: '28 - 06 - 2025', titre: 'Collecte de croquettes et litières' },
    { date: '15 - 07 - 2025', titre: 'Atelier sensibilisation à l\'adoption' },
    { date: '02 - 08 - 2025', titre: 'Marché solidaire au profit du refuge' },
  ];
}
