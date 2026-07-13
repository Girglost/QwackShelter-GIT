import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Role } from '../enum/role';
import { StatutActivite } from '../enum/statut-activite';
import { Personne } from '../model/personne';

@Injectable({
  providedIn: 'root',
})
export class PersonneService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = "/personne";

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

  public findAll(): Observable<Personne[]> {
    return this.http.get<Personne[]>(this.apiUrl);
  }

  public add(p: Personne): Observable<Personne> {
    const url = this.getUrlByRole(p.role);
    return this.http.post<Personne>(url, p);
  }

  public update(p: Personne): Observable<Personne> {
    const url = this.getUrlByRole(p.role);
    return this.http.put<Personne>(`${url}/${p.id}`, p);
  }

  public remove(p: Personne): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${p.id}`);
  }

  public findByRole(role: Role): Observable<Personne[]> {
    if (role == Role.PATRON) {
      return this.http.get<Personne[]>(`${this.apiUrl}/${role.toLowerCase()}`);
    }
    return this.http.get<Personne[]>(`/${role.toLowerCase()}`);
  }

  public findByQuackShelter(idQuackShelter: number): Observable<Personne[]> {
    return this.http.get<Personne[]>(`${this.apiUrl}/quackshelter/${idQuackShelter}`);
  }

  public findByStatutActivite(statut: StatutActivite): Observable<Personne[]> {
    return this.http.get<Personne[]>(`${this.apiUrl}/statut/${statut}`);
  }

  public findByAdmin(admin: boolean): Observable<Personne[]> {
    return this.http.get<Personne[]>(`${this.apiUrl}/admin`);
  }
}
