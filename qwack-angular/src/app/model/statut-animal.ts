import { Statut } from "../enum/statut";
import { StatutValidation } from "../enum/statut-validation";
import { Emplacement } from "./emplacement";

export interface StatutAnimal {
  id: number;
  dateArrivee: Date;
  dateDepart: Date;
  statut: Statut;
  emplacement: Emplacement;
  statutAdoption: StatutValidation;
  animalId: number;
}

