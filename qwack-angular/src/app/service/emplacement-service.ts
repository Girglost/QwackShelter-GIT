import { TypeBox } from './../model/type-box';
import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Emplacement } from '../model/emplacement';

@Injectable({
  providedIn: 'root',
})
export class EmplacementService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = "http://localhost:8080/api/emplacement";

  public findAll(): Observable<Emplacement[]> {
    return this.http.get<Emplacement[]>(this.apiUrl);
  }

  public add(emplacement: Emplacement): Observable<Emplacement> {
    return this.http.post<Emplacement>(this.apiUrl, emplacement);
  }

  public update(emplacement: Emplacement): Observable<Emplacement> {
    return this.http.put<Emplacement>(`${ this.apiUrl }/${ emplacement.id }`, emplacement);
  }

  public remove(emplacement: Emplacement): Observable<void> {
    return this.http.delete<void>(`${ this.apiUrl }/${ emplacement.id }`);
  }

  public findDispo(): Observable<Emplacement[]> {
    return this.http.get<Emplacement[]>(`${this.apiUrl}/dispo`);
  }

  public findComplet(): Observable<Emplacement[]> {
    return this.http.get<Emplacement[]>(`${this.apiUrl}/complet`);
  }

  public findByBox(box:TypeBox): Observable<Emplacement[]> {
    return this.http.get<Emplacement[]>(`${this.apiUrl}/box/${box}`);
  }

}
