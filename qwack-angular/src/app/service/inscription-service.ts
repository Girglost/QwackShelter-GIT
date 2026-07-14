import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Role } from "../enum/role";
import { InscriptionRequestDto } from "../model/inscription-request-dto";
import { InscriptionResponseDto } from "../model/inscription-response-dto";
import { PersonneService } from "./personne-service";
@Injectable({
    providedIn: 'root',
})
export class InscriptionService {
    private http: HttpClient = inject(HttpClient);
    private personneSrv: PersonneService = inject(PersonneService);


    // Distinguer les roles, on va chercher le bon controller
    //  AddVisiteur, AddBenevole etc...
    private getUrlByRole(role: Role): string {

        switch (role) {
            case Role.EMPLOYE:
                return "/employe";

            case Role.BENEVOLE:
                return "/benevole";

            case Role.PATRON:
                return "/patron";

            case Role.VISITEUR:
                return "/visiteur";

            default:
                return "/personne";
        }
    }

    public inscription(request: InscriptionRequestDto): Promise<void> {


        const url = this.getUrlByRole(request.role);
        return new Promise((resolve, reject) => {

            this.http.post<InscriptionResponseDto>(url, request).subscribe({
                // next => si la réponse est OK
                next: resp => {
                    resolve();
                },

                // error => si la réponse est KO (30X, 40X, 50X)
                error: err => reject(err)
            });
        })
    }
}
