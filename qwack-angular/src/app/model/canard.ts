import { Famille } from '../enum/famille';
import { Genre } from '../enum/genre';
import { Caractere } from '../enum/caractere';
import { QuackShelter } from './quack-shelter';

export interface Canard {
  id?: number;
  nomAnimal: string;
  dateNaissance: Date;
  couleur: string;
  regimeAlimentaire: string;
  traitement: string;
  famille: Famille;
  genre: Genre;
  caracteres: Caractere[];
  quackShelter: QuackShelter;
  capaciteVol: boolean;
  pondeuse: boolean;
  race: string;
  estSauvage: boolean;
}
