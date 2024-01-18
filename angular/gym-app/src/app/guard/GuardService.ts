import {Router} from "@angular/router";
import {Injectable} from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class GuardService {
  private currentUserRole: string = "";


  constructor(private router:Router) {
  }

  public setUserRole(roleId:number) {
    this.currentUserRole = roleId==1?"trainer":"trainee";
    localStorage.setItem("userRole",this.currentUserRole);
  }

  isUserTrainee(): boolean {
    return localStorage.getItem("userRole") === "trainee";
  }


  isUserTrainer(): boolean {
    return localStorage.getItem("userRole") === "trainer";
  }

  isTokenPresent() {
    return localStorage.getItem("userRole") === "";
  }

  redirectToHome(){
    localStorage.clear();
    this.router.navigate(['']);
  }
}
