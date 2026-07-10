import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Lieu } from '../model/lieu';
import { TypeLieu } from '../enum/type-lieu';

@Injectable({
  providedIn: 'root',
})
export class LieuService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = "http://localhost:8080/api/lieu";

  public findAll(): Observable<Lieu[]> {
    return this.http.get<Lieu[]>(this.apiUrl);
  }

  public add(l: Lieu): Observable<Lieu> {
    return this.http.post<Lieu>(this.apiUrl, l);
  }

  public update(l: Lieu): Observable<Lieu> {
    return this.http.put<Lieu>(`${this.apiUrl}/${l.id}`, l);
  }

  public remove(l: Lieu): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${l.id}`);
  }

  public findByTypeLieu(type: TypeLieu): Observable<Lieu[]> {
    return this.http.get<Lieu[]>(`${this.apiUrl}/type/${type}`);
  }
}
