import { StatutValidation } from "../enum/statut-validation";

export interface Visite {

    id: number;
    ateVisite: Date;
    idVisiteur: number;
    nomVisiteur: string;

    idAnimal: number;
    nomAnimal: string;

    idQuackShelter: number;

    statutVisite: StatutValidation;
}
