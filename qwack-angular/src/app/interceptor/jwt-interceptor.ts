import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '../service/auth-service';

export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  console.log("URL interceptor :", req.url);
  console.log("JWT :", authService.token());


  if (req.url.endsWith('/api/auth')) {
    return next(req);
  }

  const jwtRequest = req.clone({
    setHeaders: {
      'Authorization': `Bearer ${authService.token()}`
    }
  });
  console.log("Headers envoyés :", jwtRequest.headers);
  return next(jwtRequest);
};
