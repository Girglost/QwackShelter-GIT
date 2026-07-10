import { Animal } from './animal';
import { Cause } from '../enum/cause';

export interface HistoriqueSante {
  id: number;
  cause: Cause;
  date: Date;
  heure: string; // format "HH:mm" côté formulaire
  poids: number;
  commentaire: string;
  animal: Animal;
}
