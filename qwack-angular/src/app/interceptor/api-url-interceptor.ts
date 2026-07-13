import { HttpInterceptorFn } from '@angular/common/http';

export const apiUrlInterceptor: HttpInterceptorFn = (req, next) => {
  console.log("INTERCEPTION !!");

  const apiRequest = req.clone({
    url: `http://localhost:8080/api${req.url}`
  });

  console.log(" API REQUEST", apiRequest)

  return next(apiRequest);
};
