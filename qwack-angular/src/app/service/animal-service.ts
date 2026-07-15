import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Animal } from '../model/animal';
import { CreateAnimalRequest } from '../model/create-animal-request';
import { UpdateAnimalRequest } from '../model/update-animal-request';
import { TypeAnimal } from '../enum/type-animal';

@Injectable({
  providedIn: 'root',
})
export class AnimalService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = '/animal';

  public findAll(): Observable<Animal[]> {
    return this.http.get<Animal[]>(this.apiUrl);
  }

  public findById(id: number): Observable<Animal> {
    return this.http.get<Animal>(`${this.apiUrl}/${id}`);
  }

  public add(req: CreateAnimalRequest): Observable<Animal> {
    const url = this.resoudreUrl(req.type_animal);
    return this.http.post<Animal>(url, req);
  }

  public update(id: number, req: UpdateAnimalRequest, typeAnimal: TypeAnimal): Observable<Animal> {
    const url = this.resoudreUrl(typeAnimal);
    return this.http.put<Animal>(`${url}/${id}`, req);
  }

  public remove(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  public findDisponibles(): Observable<Animal[]> {
    return this.http.get<Animal[]>(`${this.apiUrl}/disponible/PRESENT`);
  }

  public findPresent(): Observable<Animal[]> {
    return this.http.get<Animal[]>(`${this.apiUrl}/present`);
  }

  private resoudreUrl(typeAnimal: string): string {
    switch (typeAnimal) {
      case TypeAnimal.Chat:
        return '/chat';
      case TypeAnimal.Chien:
        return '/chien';
      case TypeAnimal.Canard:
        return '/canard';
      case TypeAnimal.Poule:
        return '/poule';
      case TypeAnimal.NAC:
        return '/nac';
      default:
        throw new Error(`Type d'animal inconnu : ${typeAnimal}`);
    }
  }
}
