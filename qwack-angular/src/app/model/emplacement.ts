import { TypeBox } from "../enum/type-box";

export interface Emplacement {
  id?: number;
  nb_place: number;
  complet: boolean;
  box: TypeBox
}
