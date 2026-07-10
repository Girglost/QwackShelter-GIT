import { Famille } from '../enum/famille';
import { Genre } from '../enum/genre';
import { Caractere } from '../enum/caractere';

export interface CreateAnimalRequest {
  nomAnimal: string;
  dateNaissance: string; // format "yyyy-MM-dd" pour LocalDate
  couleur: string;
  regimeAlimentaire: string;
  traitement: string;
  famille: Famille;
  genre: Genre;
  caracteres: Caractere[];
  qwackShelterId: number;
  capaciteVol: boolean;
  pondeuse: boolean;
  race: string;
  sterilisation: boolean;
  gestante: boolean;
  estSauvage: boolean;
  espece: string;
}
