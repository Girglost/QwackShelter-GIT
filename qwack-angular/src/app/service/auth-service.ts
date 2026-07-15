import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { AuthRequestDto } from '../model/auth-request-dto';
import { AuthResponseDto } from '../model/auth-response-dto';
import { CurrentUser } from '../model/current-user';
import { PersonneService } from './personne-service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http: HttpClient = inject(HttpClient);
  private personneSrv: PersonneService = inject(PersonneService);
  private _token = signal(sessionStorage.getItem("token") ?? "");

  //Charger le currentUser depuis la session
  private _currentUser = signal<CurrentUser | null>(
    JSON.parse(sessionStorage.getItem("currentUser") ?? "null")
  );

  public token() {
    return this._token();
  }
  public currentUser() {
    return this._currentUser();
  }

  public setToken(value: string) {
    this._token.set(value);
    sessionStorage.setItem("token", value);
  }

  public isLogged() {
    return !!this._token();
  }

  public resetAuth() {
    this._token.set("");
    this._currentUser.set(null);

    sessionStorage.removeItem("token");
    sessionStorage.removeItem("currentUser");
  }
  public loadCurrentUser(): Observable<CurrentUser> {

    return this.personneSrv.getMe()
      .pipe(
        tap(user => {
          console.log("USER CONNECTED ", user)
          this._currentUser.set(user);
          // Le sauvegarder en session
          sessionStorage.setItem(
            "currentUser",
            JSON.stringify(user)
          );
        })
      );

  }


  public auth(request: AuthRequestDto): Observable<void> {
    return new Observable<void>(observer => {
      this.http.post<AuthResponseDto>('/auth', request).subscribe({
        next: resp => {
          this.setToken(resp.token);
          this.loadCurrentUser().subscribe({
            next: () => {
              observer.next();
              observer.complete();
            },
            error: () => {
              observer.error();
            }
          });
        },

        error: () => {
          observer.error();
        }
      });
    });
  }

  // au chargement de la page, on va récupérer le user connecté
  public initialize() {
    if (this.isLogged()) {
      this.loadCurrentUser().subscribe();
    }
  }
}
