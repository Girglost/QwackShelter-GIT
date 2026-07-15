import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Statut } from '../enum/statut';
import { StatutAnimal } from '../model/statut-animal';
import { CreateStatutAnimalRequest } from '../model/create-statut-animal-request';


@Injectable({
  providedIn: 'root',
})
export class StatutAnimalService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/statutAnimal';

  public findAll(): Observable<StatutAnimal[]> {
    return this.http.get<StatutAnimal[]>(this.apiUrl);
  }

  public add(sAnimal: CreateStatutAnimalRequest): Observable<CreateStatutAnimalRequest> {
    return this.http.post<CreateStatutAnimalRequest>(this.apiUrl, sAnimal);
  }

  public update(sAnimal: StatutAnimal): Observable<StatutAnimal> {
    return this.http.put<StatutAnimal>(`${this.apiUrl}/${sAnimal.id}`, sAnimal);
  }

  public remove(sAnimal: StatutAnimal): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${sAnimal.id}`);
  }

  public findByStatut(statut: Statut): Observable<StatutAnimal[]> {
    return this.http.get<StatutAnimal[]>(`${this.apiUrl}/statut/${statut}`);
  }
}
