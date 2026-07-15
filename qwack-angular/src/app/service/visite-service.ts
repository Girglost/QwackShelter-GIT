import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Visite } from "../model/visite";
import { VisiteRequest } from "../model/visite-request";

@Injectable({
    providedIn: 'root',
})
export class VisiteService {
    private http: HttpClient = inject(HttpClient);

    public demanderVisite(
        demande: VisiteRequest
    ): Observable<Visite> {
        return this.http.post<Visite>('/visiteur/visiter', demande);
    }
}
