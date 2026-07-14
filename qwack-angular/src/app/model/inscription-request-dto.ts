import { Role } from "../enum/role";
import { Lieu } from "./lieu";

export interface InscriptionRequestDto {
    nom: string;

    prenom: string;

    login: string;

    password: string;

    habitation: Lieu;

    quackShelterId: number;
    role: Role;



}
