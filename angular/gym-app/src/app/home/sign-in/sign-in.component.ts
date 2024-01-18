import {Component} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CredentialsDto} from "../../model/CredentialsDto";
import {Router} from "@angular/router";
import {SnackBarService} from "../../../service/snack-bar.service";
import {SigninService} from "../../../service/signin.service";
import {TraineeService} from "../../../service/trainee.service";
import {TrainerService} from "../../../service/trainer.service";
import {GuardService} from "../../guard/GuardService";
import {TrainerProfileDto} from "../../model/TrainerProfileDto";
import {TraineeProfileDto} from "../../model/TraineeProfileDto";

@Component({
  selector: 'app-sign-in',
  templateUrl: './sign-in.component.html',
  styleUrls: ['./sign-in.component.css']
})
export class SignInComponent {
  credentials: CredentialsDto = new CredentialsDto();
  loginForm: any
  selectedUserType: string = 'trainee';


  constructor(private router: Router, private snackbarService: SnackBarService, private signInService: SigninService, private traineeService: TraineeService, private trainerService: TrainerService
  ,private guardService:GuardService) {
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
      userType:new FormControl('trainee')
    });
  }


  get username() {
    return this.loginForm.get('username');
  }

  get password() {
    return this.loginForm.get('password');
  }

  onSubmit() {
    this.credentials.username = this.loginForm.value.username;
    this.credentials.password = this.loginForm.value.password;
    this.signInService.userAuthentication(this.credentials).subscribe({
      next: (data: any) => {
        if(this.loginForm.value.userType=='trainer')
        {
          this.trainerService.getTrainerProfile(this.credentials.username).subscribe({
            next:(data: any)=>{
              let trainerProfile: TrainerProfileDto = data
              this.handleSuccessfulAuthentication(trainerProfile, 1, '/trainer/trainer-profile');
            },
            error:(error:any)=>{
              this.handleProfileNotFoundError("Trainer",this.credentials.username);
            }
          })
        }
        else if(this.loginForm.value.userType=='trainee')
        {
          this.traineeService.getTraineeProfile(this.credentials.username).subscribe({
            next:(data: any)=>{
              let traineeProfile: TraineeProfileDto = data
              console.log(traineeProfile);
              this.handleSuccessfulAuthentication(traineeProfile, 2, '/trainee/trainee-profile');
            },
            error:(error:any)=>{
              this.handleProfileNotFoundError("Trainee",this.credentials.username);
            }
          })
        }
      },
      error: (error: any) => {
        this.snackbarService.openSnackBar(`Enter Valid Username and Password`);
      }
    });

  }

  handleProfileNotFoundError(userType: string, username: string |undefined): void {
    this.snackbarService.openSnackBar(`${userType} with username ${username} not found.`);
  }

  handleSuccessfulAuthentication(profile: TrainerProfileDto|TraineeProfileDto, userRole: number, route: string): void {
    this.guardService.setUserRole(userRole);
    this.router.navigate([route], { state: { profile } });
  }
}
