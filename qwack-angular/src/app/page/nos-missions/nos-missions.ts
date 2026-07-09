import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';

export interface Mission {
  title: string;
  icon: string;
  description: string;
  details: string;
}

@Component({
  selector: 'app-nos-missions',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './nos-missions.html',
  styleUrl: './nos-missions.css',
})
export class NosMissions {

  readonly missions: Mission[] = [

    {
      title: 'Sauvetage des animaux',
      icon: '🐾',
      description:
        'Prendre en charge les animaux abandonnés, perdus ou victimes de maltraitance.',
      details:
        'Accueil, évaluation du comportement et mise en place des premières actions nécessaires à leur bien-être.'
    },

    {
      title: 'Familles d’accueil',
      icon: '🏠',
      description:
        'Offrir un environnement temporaire aux animaux avant leur adoption.',
      details:
        'Les familles d’accueil jouent un rôle essentiel pour les animaux qui ont besoin de calme, de soins ou de socialisation.'
    },

    {
      title: 'Soins et bien-être',
      icon: '❤️',
      description:
        'Veiller au confort et à la santé quotidienne des animaux du refuge.',
      details:
        'Distribution des repas, soins courants, administration des traitements prescrits et observation des animaux.'
    },

    {
      title: 'Promenades',
      icon: '🐶',
      description:
        'Permettre aux chiens de sortir, se dépenser et profiter de moments privilégiés.',
      details:
        'Les promenades contribuent à leur équilibre physique et émotionnel avant leur future adoption.'
    },

    {
      title: 'Adoption responsable',
      icon: '🤝',
      description:
        'Accompagner les familles dans leur projet d’adoption.',
      details:
        'Rencontres avec les animaux, conseils personnalisés et suivi après adoption.'
    },

    {
      title: 'Communication',
      icon: '📢',
      description:
        'Faire connaître les animaux et les actions de l’association.',
      details:
        'Création de contenus, réseaux sociaux, photos, événements et sensibilisation du public.'
    },

    {
      title: 'Collectes et événements',
      icon: '🎪',
      description:
        'Participer aux actions permettant de soutenir le refuge.',
      details:
        'Organisation de collectes alimentaires, journées portes ouvertes et manifestations solidaires.'
    },

    {
      title: 'Entretien du refuge',
      icon: '🧹',
      description:
        'Maintenir un environnement propre et agréable pour les animaux.',
      details:
        'Nettoyage des espaces de vie, rangement et aide à l’organisation quotidienne.'
    }

  ];

}

