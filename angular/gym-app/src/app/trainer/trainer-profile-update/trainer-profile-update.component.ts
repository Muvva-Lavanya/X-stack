import {Component} from '@angular/core';
import {TrainerProfileDto} from "../../model/TrainerProfileDto";
import {TrainerUpdateDto} from "../../model/TrainerUpdateDto";
import {Router} from "@angular/router";
import {TrainerService} from "../../../service/trainer.service";
import {SnackBarService} from "../../../service/snack-bar.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-trainer-profile-update',
  templateUrl: './trainer-profile-update.component.html',
  styleUrls: ['./trainer-profile-update.component.css']
})
export class TrainerProfileUpdateComponent {
  trainer: TrainerProfileDto = new TrainerProfileDto();
  trainerUpdateProfile: TrainerUpdateDto = new TrainerUpdateDto();
  updationForm:any;

  constructor(private router: Router, private trainerService: TrainerService, private snackbarService: SnackBarService) {

  }

  ngOnInit() {
    const state = window.history.state;
    this.trainer = state.trainerProfile;
    this.updationForm = new FormGroup({
      firstName: new FormControl(this.trainer.firstName, [Validators.required,Validators.minLength(3),
        Validators.maxLength(15),
        Validators.pattern(/^[a-zA-Z]*$/)]),
      lastName: new FormControl(this.trainer.lastName, [Validators.required,Validators.minLength(3),
        Validators.maxLength(15),
        Validators.pattern(/^[a-zA-Z]*$/)]),
      status: new FormControl(this.trainer.status, Validators.required),
      email: new FormControl(this.trainer.email, [Validators.required, Validators.pattern(/^[a-zA-Z0-9]+@gmail\.com$/i)]),
    });
  }

  onSubmit() {
    console.log(this.trainer);
    //Details to update trainer-profile
    this.trainerUpdateProfile.username = this.trainer.userName;
    this.trainerUpdateProfile.firstName = this.updationForm.value.firstName;
    this.trainerUpdateProfile.lastName = this.updationForm.value.lastName;
    this.trainerUpdateProfile.specialization = this.trainer.specialization;
    this.trainerUpdateProfile.email = this.updationForm.value.email;
    this.trainerUpdateProfile.status = this.updationForm.value.status;

    //Details to get reflected to profile automatically
    this.trainer.firstName = this.updationForm.value.firstName;
    this.trainer.lastName = this.updationForm.value.lastName;
    this.trainer.email = this.updationForm.value.email;
    this.trainer.status = this.updationForm.value.status;
    this.trainerService.updateTraineeProfile(this.trainerUpdateProfile).subscribe({
      next:(data:any)=>{
        this.snackbarService.openSnackBar(`Trainer Profile Updated Successfully`);
        this.router.navigate(['/trainer/trainer-profile'], {state: {profile: this.trainer}});
      },error:(error:any)=>{
        this.snackbarService.openSnackBar(error.error.error);
      }
    })
  }

  backToProfile() {
    this.router.navigate(['/trainer/trainer-profile'], {state: {profile: this.trainer}});
  }
}
