import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AdoptionRequest } from "../model/adoption-request";
import { StatutAnimal } from "../model/statut-animal";

@Injectable({
    providedIn: 'root',
})
export class AdoptionService {

    private http: HttpClient = inject(HttpClient);

    public demanderAdoption(
        demande: AdoptionRequest, role: string
    ): Observable<StatutAnimal> {
        switch (role) {

            case "VISITEUR":
                return this.http.post<StatutAnimal>('/visiteur/adopter', demande);

            case "BENEVOLE":
                return this.http.post<StatutAnimal>('/benevole/adopter', demande);
            default:
                throw new Error(`Le ${role} n'est pas autorisé a faire une demande d'adoption`);
        }


    }

}
