import { Lieu } from "./lieu";

export interface CurrentUser {
    id: number;
    nom: string;
    prenom: string;
    login: string;
    role: string;
    admin: boolean;
    habitation?: Lieu;
    quackShelterId?: number;
}