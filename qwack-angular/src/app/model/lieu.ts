import { TypeLieu } from '../enum/type-lieu';
import { Adresse } from './adresse';

export interface Lieu {
  typeLieu: TypeLieu;
  adresse: Adresse;
}
