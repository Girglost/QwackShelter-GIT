import { TypeBox } from "./type-box";

export interface Emplacement {
  id?: number;
  nbPlace: number;
  complet: boolean;
  box: TypeBox
}
