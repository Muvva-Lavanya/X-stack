import { CanActivateFn } from '@angular/router';
import {inject} from "@angular/core";
import {GuardService} from "./GuardService";

export const trainerGuard: CanActivateFn = (route, state) => {
  const roleGuardService = inject(GuardService);
  if(roleGuardService.isUserTrainer()){
    return true;
  }else{
    if (roleGuardService.isTokenPresent()) {
      alert("Please Login First");
      roleGuardService.redirectToHome();
      return false;
    } else {
      alert("Access Denied");
      roleGuardService.redirectToHome();
      return false;
    }
  }
};
