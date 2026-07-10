import { Famille } from '../enum/famille';
import { Genre } from '../enum/genre';
import { Caractere } from '../enum/caractere';
import { TypeAnimal } from '../enum/type-animal';
import { QuackShelter } from './quack-shelter';
import { HistoriqueSante } from './historique-sante';
import { StatutAnimal } from './statut-animal';

// Interface "à plat" volontairement peu stricte sur les champs spécifiques
// pour simplifier l'affichage table/formulaire. Les services par sous-type
// (Chat/Chien/Canard/NAC/Poule) utilisent des interfaces plus strictes pour l'ajout/update.
export interface Animal {
  id: number;
  typeAnimal: TypeAnimal;
  nomAnimal: string;
  dateNaissance: Date;
  couleur: string;
  regimeAlimentaire: string;
  traitement: string;
  famille: Famille;
  genre: Genre;
  caracteres: Caractere[];
  quackShelter: QuackShelter;
  historiqueSante: HistoriqueSante[];
  statutAnimal: StatutAnimal;
  visites: any[];

  // Champs spécifiques Chat/Chien/NAC
  sterilisation?: boolean;
  gestante?: boolean;
  race?: string;

  // Champs spécifiques Canard/Poule
  capaciteVol?: boolean;
  pondeuse?: boolean;
  estSauvage?: boolean; // uniquement Canard
}
