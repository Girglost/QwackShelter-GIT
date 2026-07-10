import { TypeBox } from "../enum/type-box";

export interface Emplacement {
  id?: number;
  nbPlace: number;
  complet: boolean;
  box: TypeBox
}
