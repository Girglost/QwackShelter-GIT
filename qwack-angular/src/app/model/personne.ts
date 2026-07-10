import { Lieu } from './lieu';
import { Role } from '../enum/role';
import { StatutActivite } from '../enum/statut-activite';
import { QuackShelter } from './quack-shelter';

export interface Personne {
  id: number;
  nom: string;
  prenom: string;
  login: string;
  password: string;
  habitation: Lieu;
  role: Role;
  admin: boolean;
  statutActivite: StatutActivite;
  quackshelter: QuackShelter;
  dateInscription: Date;
  dateEngagement: Date;
  salaire: number;
  dateEmbauche: Date;
}
