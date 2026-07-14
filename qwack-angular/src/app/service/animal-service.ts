import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Animal } from '../model/animal';
import { CreateAnimalRequest } from '../model/create-animal-request';
import { UpdateAnimalRequest } from '../model/update-animal-request';

@Injectable({
  providedIn: 'root',
})
export class AnimalService {
  [x: string]: any;
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = "/animal";

  public findAll(): Observable<Animal[]> {
    return this.http.get<Animal[]>(this.apiUrl);
  }

  public findById(id: number): Observable<Animal> {
    return this.http.get<Animal>(`${this.apiUrl}/${id}`);
  }

  public add(req: CreateAnimalRequest): Observable<Animal> {
    return this.http.post<Animal>(this.apiUrl, req);
  }

  public update(id: number, req: UpdateAnimalRequest): Observable<Animal> {
    return this.http.put<Animal>(`${this.apiUrl}/${id}`, req);
  }

  public remove(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  public findDisponibles(): Observable<Animal[]> {
  return this.http.get<Animal[]>(`${this.apiUrl}/statut/PRESENT`);
}
}
