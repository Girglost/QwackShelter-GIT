import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { AdoptionRequest } from "../model/adoption-request";
import { StatutAnimal } from "../model/statut-animal";
import { AnimalService } from "./animal-service";

@Injectable({
    providedIn: 'root',
})
export class AdoptionService {

    private http: HttpClient = inject(HttpClient);
    private animalSrv: AnimalService = inject(AnimalService);

    public demanderAdoption(
        demande: AdoptionRequest
    ): Observable<StatutAnimal> {

        return this.http.post<StatutAnimal>(`/visiteur/adopter`, demande);

    }

}
