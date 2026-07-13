import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cause } from '../enum/cause';
import { HistoriqueSante } from '../model/historique-sante';

@Injectable({
  providedIn: 'root',
})
export class HistoriqueSanteService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = "/hSante";

  public findAll(): Observable<HistoriqueSante[]> {
    return this.http.get<HistoriqueSante[]>(this.apiUrl);
  }

  public add(h: HistoriqueSante): Observable<HistoriqueSante> {
    return this.http.post<HistoriqueSante>(this.apiUrl, h);
  }

  public update(h: HistoriqueSante): Observable<HistoriqueSante> {
    return this.http.put<HistoriqueSante>(`${this.apiUrl}/${h.id}`, h);
  }

  public remove(h: HistoriqueSante): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${h.id}`);
  }

  public findByCause(cause: Cause): Observable<HistoriqueSante[]> {
    return this.http.get<HistoriqueSante[]>(`${this.apiUrl}/cause/${cause}`);
  }

  public findByAnimal(idAnimal: number): Observable<HistoriqueSante[]> {
    return this.http.get<HistoriqueSante[]>(`${this.apiUrl}/animal/${idAnimal}`);
  }
}
