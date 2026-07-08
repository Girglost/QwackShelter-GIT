import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-actualites',
  imports: [FormsModule],
  templateUrl: './actualites.html',
  styleUrl: './actualites.css',
})
export class Actualite implements OnInit{

  actualites = [
    {
      titre: 'Journée portes ouvertes !',
      date: '15 - 07 - 2025',
      description: 'Venez visiter le QuackShelter, rencontrer nos animaux et échanger avec nous.',
      image: '/assets/image/actualites/porte-ouverte.png'
    },
    {
      titre: 'Collecte de nourriture',
      date: 'Tout le mois de Juillet',
      description: 'Déposez vos dons au shelter pendant tout le mois de Juillet.',
      image: '/assets/images/actualites/collecte.jpg'
    },
    {
      titre: 'Rencontre vétérinaire',
      date: '24 - 06 - 2025',
      description: 'Rendez-vous au refuge pour échanger avec notre vétérinaire partenaire qui répondra à vos questions.',
      image: '/assets/images/actualites/veterinaire.jpg'
    },
    {
      titre: 'Réaménagement de l\'espace NAC',
      date: '18 - 04 - 2025',
      description: 'Grâce à nos bénévoles, un nouvel espace a été aménagé pour accueillir nos NAC.',
      image: 'assets/images/actualites/lapin.jpg'
    },
    {
      titre: 'CaniCross QuackShelter',
      date: '11 - 04 - 2025',
      description: 'Chaussez vos baskets et venez courir avec nos pensionnaires lors d\'une journée sportive.',
      image: '/assets/images/actualites/canicross.jpg'
    },
    {
      titre: 'Nouveaux pensionnaires',
      date: '05 - 03 - 2025',
      description: 'Le refuge accueille désormais plusieurs équidés grâce au soutien de Jordan Renov.',
      image: '/assets/images/actualites/chevaux.jpg'
    }
  ];

  email = '';

  constructor() { }

  ngOnInit(): void {
  }

  inscrireNewsletter() {
    if (this.email.trim() !== '') {
      alert('Merci pour votre inscription !');
      this.email = '';
    }
  }

}

