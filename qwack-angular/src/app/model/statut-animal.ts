import { Animal } from "../model/animal";
import { Emplacement } from "./emplacement";
import { Statut } from "./statut";
import { StatutValidation } from "./statut-validation";

export interface StatutAnimal {
  id: number;
  dateArrivee: Date;
  dateDepart: Date;
  statut: Statut;
  emplacement: Emplacement;
  statutAdoption: StatutValidation;
  animal: Animal;
}

export type { Animal };

