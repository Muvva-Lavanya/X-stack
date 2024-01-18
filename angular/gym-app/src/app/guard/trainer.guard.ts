import { CanActivateFn } from '@angular/router';
import {inject} from "@angular/core";
import {GuardService} from "./GuardService";

export const traineeGuard: CanActivateFn = (route, state) => {
  const roleGuardService = inject(GuardService);
  if (roleGuardService.isUserTrainee()) {
    return true;
  } else {
    if (roleGuardService.isTokenPresent()) {
      alert("Please Login First");
      roleGuardService.redirectToHome();
      return false;
    } else {
      alert("Access Denied");
      return false;
    }
  }
};
