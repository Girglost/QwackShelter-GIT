import { Role } from '../enum/role';
import { StatutActivite } from '../enum/statut-activite';
import { Lieu } from './lieu';

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
  quackShelterId: number;
  dateInscription: Date;
  dateEngagement: Date;
  salaire: number;
  dateEmbauche: Date;
}
