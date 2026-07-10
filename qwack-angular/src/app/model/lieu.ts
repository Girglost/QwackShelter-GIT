import { TypeLieu } from '../enum/type-lieu';
import { Adresse } from './adresse';

export interface Lieu {
  id: number;
  typeLieu: TypeLieu;
  adresse: Adresse;
}
