import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QuackShelter } from '../model/quack-shelter';

@Injectable({
  providedIn: 'root',
})
export class QuackShelterService {
  private http: HttpClient = inject(HttpClient);
  private apiUrl: string = "http://localhost:8080/api/quackshelter";

  public findAll(): Observable<QuackShelter[]> {
    return this.http.get<QuackShelter[]>(this.apiUrl);
  }
}
