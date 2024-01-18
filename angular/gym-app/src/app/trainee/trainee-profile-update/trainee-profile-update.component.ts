import {Component} from '@angular/core';
import {TraineeUpdateDto} from "../../model/TraineeUpdateDto";
import {TraineeProfileDto} from "../../model/TraineeProfileDto";
import {Router} from "@angular/router";
import {TraineeService} from "../../../service/trainee.service";
import {SnackBarService} from "../../../service/snack-bar.service";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-trainee-profile-update',
  templateUrl: './trainee-profile-update.component.html',
  styleUrls: ['./trainee-profile-update.component.css']
})
export class TraineeProfileUpdateComponent {
  traineeProfile: TraineeProfileDto = new TraineeProfileDto();
  traineeUpdateDto: TraineeUpdateDto = new TraineeUpdateDto();
  updationForm:any;
  constructor(private router: Router, private traineeService: TraineeService, private snackbarService: SnackBarService) {
  }
  ngOnInit() {
    const state = window.history.state;
    this.traineeProfile = state.traineeProfile;
    this.updationForm = new FormGroup({
      firstName: new FormControl(this.traineeProfile.firstName, [Validators.required,Validators.minLength(3),
        Validators.maxLength(15),
        Validators.pattern(/^[a-zA-Z]*$/)]),
      lastName: new FormControl(this.traineeProfile.lastName, [Validators.required,Validators.minLength(3),
        Validators.maxLength(15),
        Validators.pattern(/^[a-zA-Z]*$/)]),
      dateOfBirth:new FormControl(this.traineeProfile.dateOfBirth,Validators.required),
      address:new FormControl(this.traineeProfile.address,Validators.required),
      status: new FormControl(this.traineeProfile.status, Validators.required),
      email: new FormControl(this.traineeProfile.email, [Validators.required, Validators.pattern(/^[a-zA-Z0-9]+@gmail\.com$/i)]),
    });
  }

  onSubmit() {
    //updating inputDto
    this.traineeUpdateDto.username = this.traineeProfile.userName;
    this.traineeUpdateDto.firstName = this.updationForm.value.firstName;
    this.traineeUpdateDto.lastName = this.updationForm.value.lastName;
    this.traineeUpdateDto.dateOfBirth = this.traineeProfile.dateOfBirth;
    this.traineeUpdateDto.address = this.updationForm.value.address;
    this.traineeUpdateDto.email = this.updationForm.value.email;
    this.traineeUpdateDto.status = this.updationForm.value.status;

    //updating state Dto
    this.traineeProfile.firstName=this.updationForm.value.firstName;
    this.traineeProfile.lastName=this.updationForm.value.lastName;
    this.traineeProfile.address=this.updationForm.value.address;
    this.traineeProfile.email=this.updationForm.value.email;
    this.traineeService.updateTraineeProfile(this.traineeUpdateDto).subscribe({
      next:(data:any)=>{
        this.snackbarService.openSnackBar(`Trainee Profile Updated Successfully`);
        this.router.navigate(['/trainee/trainee-profile'], {state: {profile: this.traineeProfile}});
      },error:(error:any)=>{
        this.snackbarService.openSnackBar(error.error.error);
      }
    })


  }

  backToProfile() {
    this.router.navigate(['/trainee/trainee-profile'], {state: {profile: this.traineeProfile}});
  }
}
