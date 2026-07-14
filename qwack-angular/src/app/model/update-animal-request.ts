import { Genre } from '../enum/genre';
import { Caractere } from '../enum/caractere';

export interface UpdateAnimalRequest {
  nomAnimal: string;
  dateNaissance: string;
  couleur: string;
  regimeAlimentaire: string;
  traitement: string;
  genre: Genre;
  caracteres: Caractere[];
  quackShelterId: number;
  capaciteVol: boolean;
  pondeuse: boolean;
  race: string;
  sterilisation: boolean;
  gestante: boolean;
  estSauvage: boolean;
  espece: string;
}
