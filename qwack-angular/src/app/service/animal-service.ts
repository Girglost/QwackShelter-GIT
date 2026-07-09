import { HttpClient } from '@angular/common/http';
import { inject, Injectable, Service } from '@angular/core';
import { Observable } from 'rxjs';
import { Statut } from '../model/statut';
import { Animal } from '../model/statut-animal';

@Injectable({
  providedIn: 'root',
})
export class AnimalService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = 'http://localhost:8080/api/sAnimal';

  public findAll(): Observable<Animal[]> {
    return this.http.get<Animal[]>(this.apiUrl);
  }

  public add(animal: Animal): Observable<Animal> {
    return this.http.post<Animal>(this.apiUrl, animal);
  }

  public update(animal: Animal): Observable<Animal> {
    return this.http.put<Animal>(`${this.apiUrl}/${animal.id}`, animal);
  }

  public remove(animal: Animal): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${animal.id}`);
  }

  public findByStatut(statut: Statut): Observable<Animal[]> {
    return this.http.get<Animal[]>(`${this.apiUrl}/statut/${statut}`);
  }


}
