import { Role } from "../enum/role";
import { Lieu } from "./lieu";

export interface InscriptionResponseDto {
    id: number;

    nom: string;

    prenom: string;

    login: string;

    role: Role;

    habitation: Lieu;

    dateInscription?: string;

    dateEngagement?: string;
}
