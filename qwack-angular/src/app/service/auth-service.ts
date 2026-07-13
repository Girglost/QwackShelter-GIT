import { HttpClient } from '@angular/common/http';
import { inject, Injectable, signal } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthRequestDto } from '../model/auth-request-dto';
import { AuthResponseDto } from '../model/auth-response-dto';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private http: HttpClient = inject(HttpClient);
  private _token = signal(sessionStorage.getItem("token") ?? "");

  public token() {
    return this._token();
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
    sessionStorage.removeItem("token");
  }


  public auth(request: AuthRequestDto): Observable<void> {
    return new Observable<void>(observer => {
      this.http.post<AuthResponseDto>('/auth', request).subscribe({
        next: resp => {
          this._token.set(resp.token);

          // Enregistrement du jeton dans la session Storage
          sessionStorage.setItem('token', resp.token);

          observer.next();
        },

        error: () => {
          observer.error();
        }
      });
    });
  }
}
