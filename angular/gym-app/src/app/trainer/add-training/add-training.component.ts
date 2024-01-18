import {Component} from '@angular/core';
import {TrainerProfileDto} from "../../model/TrainerProfileDto";
import {TrainingDto} from "../../model/TrainingDto";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {TrainingService} from "../../../service/training.service";
import {SnackBarService} from "../../../service/snack-bar.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-training',
  templateUrl: './add-training.component.html',
  styleUrls: ['./add-training.component.css']
})
export class AddTrainingComponent {
  trainingForm: any;
  trainerProfile: TrainerProfileDto = new TrainerProfileDto();
  trainingDto: TrainingDto = new TrainingDto();

  constructor(private trainingService: TrainingService, private router: Router, private snackbarService: SnackBarService) {
    this.trainingForm = new FormGroup({
      traineeUsername: new FormControl(''),
      trainingName: new FormControl('', Validators.required),
      trainingType: new FormControl(''),
      date: new FormControl('', Validators.required),
      duration: new FormControl('', Validators.required),
    });
  }


  ngOnInit() {
    const state = window.history.state;
    this.trainerProfile = state.trainerProfile;
    console.log(this.trainerProfile);
  }

  onSubmit() {
    this.trainingDto.trainerUsername = this.trainerProfile.userName;
    this.trainingDto.traineeUsername = this.trainingForm.value.traineeUsername;
    this.trainingDto.trainingType = this.trainerProfile.specialization;
    this.trainingDto.trainingName = this.trainingForm.value.trainingName;
    this.trainingDto.date = this.trainingForm.value.date;
    this.trainingDto.duration = this.trainingForm.value.duration;
    console.log(this.trainingDto);
    this.trainingService.saveTraining(this.trainingDto).subscribe(data => {
      this.snackbarService.openSnackBar(`Training added successfully`);
      this.router.navigate(['/trainer/trainer-profile'], {state: {profile: this.trainerProfile}});
    })


  }

  backToProfile() {
    this.router.navigate(['/trainer/trainer-profile'], {state: {profile: this.trainerProfile}});
  }
}
