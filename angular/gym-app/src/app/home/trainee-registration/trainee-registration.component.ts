import {Component} from '@angular/core';
import {Trainee} from "../../model/Trainee";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TraineeService} from "../../../service/trainee.service";
import {Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {DialogBoxComponent} from "../../common-folder/dialog-box/dialog-box.component";
import {SnackBarService} from "../../../service/snack-bar.service";

@Component({
  selector: 'app-trainee-registration',
  templateUrl: './trainee-registration.component.html',
  styleUrls: ['./trainee-registration.component.css']
})
export class TraineeRegistrationComponent {
  trainee: Trainee = new Trainee();
  registrationForm: any;

  constructor(private traineeService: TraineeService, private router: Router, public dialog: MatDialog, private snackbarService: SnackBarService) {
    this.registrationForm = new FormGroup({
      firstName: new FormControl('', [Validators.required,Validators.minLength(3),
        Validators.maxLength(15),
        Validators.pattern(/^[a-zA-Z]*$/)]),
      lastName: new FormControl('', [Validators.required,Validators.minLength(3),
        Validators.maxLength(15),
        Validators.pattern(/^[a-zA-Z]*$/)]),
      dateOfBirth: new FormControl(''),
      address: new FormControl('', Validators.required),
      email: new FormControl('', [Validators.required, Validators.pattern(/^[a-zA-Z0-9]+@gmail\.com$/i)])
    });
  }

  get firstName() {
    return this.registrationForm.get('firstName');
  }

  get lastName() {
    return this.registrationForm.get('lastName');
  }

  get email() {
    return this.registrationForm.get('email');
  }

  onSubmit() {
    this.trainee.firstName = this.registrationForm.value.firstName;
    this.trainee.lastName = this.registrationForm.value.lastName;
    this.trainee.dateOfBirth = this.registrationForm.value.dateOfBirth;
    this.trainee.address = this.registrationForm.value.address;
    this.trainee.email = this.registrationForm.value.email;
    this.traineeService.saveTrainee(this.trainee).subscribe({
      next:(data:any)=>{
        this.dialog.open(DialogBoxComponent,{
          data: {username: data.username, password: data.password}
        });
      },error:(error:any)=>{
        this.snackbarService.openSnackBar(error.error.error);
      }
    })
  }
}
