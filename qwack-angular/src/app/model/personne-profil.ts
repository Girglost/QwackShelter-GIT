import { Role } from '../enum/role';
import { StatutActivite } from '../enum/statut-activite';
import { Lieu } from './lieu';
import { StatutAnimal } from './statut-animal';
import { Visite } from './visite';

export interface PersonneProfil {

    id: number;
    nom: string;
    prenom: string;
    login: string;

    habitation: Lieu;

    role: Role;
    admin: boolean;

    statutActivite: StatutActivite;

    quackShelterId: number;

    dateInscription: string;
    dateEngagement?: string;
    salaire: number;
    dateEmbauche?: string;

    visites: Visite[];
    adoptions: StatutAnimal[];

}