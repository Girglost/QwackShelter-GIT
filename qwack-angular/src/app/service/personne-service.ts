import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Personne } from '../model/personne';
import { Role } from '../enum/role';
import { StatutActivite } from '../enum/statut-activite';

@Injectable({
  providedIn: 'root',
})
export class PersonneService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = "http://localhost:8080/api/personne";

  public findAll(): Observable<Personne[]> {
    return this.http.get<Personne[]>(this.apiUrl);
  }

  public add(p: Personne): Observable<Personne> {
    return this.http.post<Personne>(this.apiUrl, p);
  }

  public update(p: Personne): Observable<Personne> {
    return this.http.put<Personne>(`${this.apiUrl}/${p.id}`, p);
  }

  public remove(p: Personne): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${p.id}`);
  }

  public findByRole(role: Role): Observable<Personne[]> {
    return this.http.get<Personne[]>(`${this.apiUrl}/role/${role}`);
  }

  public findByQuackShelter(idQuackShelter: number): Observable<Personne[]> {
    return this.http.get<Personne[]>(`${this.apiUrl}/quackshelter/${idQuackShelter}`);
  }

  public findByStatutActivite(statut: StatutActivite): Observable<Personne[]> {
    return this.http.get<Personne[]>(`${this.apiUrl}/statut/${statut}`);
  }

  public findByAdmin(admin: boolean): Observable<Personne[]> {
    return this.http.get<Personne[]>(`${this.apiUrl}/admin/${admin}`);
  }
}
