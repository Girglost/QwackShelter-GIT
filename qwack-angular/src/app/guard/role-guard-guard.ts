import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth-service';

export const roleGuardGuard: CanActivateFn = (route, state) => {


  const authService = inject(AuthService);
  const router = inject(Router);


  const user = authService.currentUser();
  const roles = route.data['roles'];
  const admin = route.data['admin']

  console.log("ROLE GUARD !!")
  console.log(roles, admin)
  if (user && roles.includes(user.role)) {
    return true;
  }
  if (admin === user?.admin) {
    return true;
  }

  return router.createUrlTree(['/access-denied']);
};
