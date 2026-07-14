import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../service/auth-service';


export const jwtInterceptor: HttpInterceptorFn = (req, next) => {

  const authService = inject(AuthService);

  const token = authService.token();


  console.log("URL interceptor :", req.url);
  console.log("JWT :", token);


  // Pas de token ou requête de connexion
  if (!token || req.url.endsWith('/api/auth')) {
    return next(req);
  }


  const jwtRequest = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`
    }
  });


  console.log("Headers envoyés :", jwtRequest.headers);


  return next(jwtRequest);
};