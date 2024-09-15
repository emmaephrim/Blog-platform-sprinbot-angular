import { ApplicationConfig, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration } from '@angular/platform-browser';
import {
  provideHttpClient,
  withFetch,
  withInterceptors,
} from '@angular/common/http';
// import { httpInterceptorProviders } from './auth/interceptor';
import { AuthInterceptor } from './auth/interceptor/auth.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideHttpClient(withFetch(), withInterceptors([AuthInterceptor])),
    // httpInterceptorProviders,
    provideRouter(routes),
    provideClientHydration(),
  ],
};
