import { inject, PLATFORM_ID } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { isPlatformBrowser, Location } from '@angular/common';

export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);
  const location = inject(Location);
  const currentUrl = location.path();

  // if (!authService.isAdmin() && isPlatformBrowser(platformId)) {
  //   return router.createUrlTree(['/']);
  // }

  // if (authService.isAdmin() && isPlatformBrowser(platformId)) {
  //   return true;
  // }
  // if (!authService.isAdmin() && isPlatformBrowser(platformId)) {
  //   return router.createUrlTree(['/']);
  // }
  // return false;

  if (isPlatformBrowser(platformId)) {
    if (authService.isAdmin()) {
      return true;
    } else {
      return router.createUrlTree(['/']);
    }
  }

  router.createUrlTree([currentUrl]);
  return false;
};

const isBrowser = (): boolean => {
  return (
    typeof window !== 'undefined' && typeof window.localStorage !== 'undefined'
  );
};
