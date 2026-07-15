import { Cause } from '../enum/cause';
import { Animal } from './animal';

export interface HistoriqueSante {
  id: number;
  cause: Cause;
  date: Date;
  heure: string; // format "HH:mm" côté formulaire
  poids: number;
  commentaire: string;
  animal?: Animal;
  animalId: number;
}
