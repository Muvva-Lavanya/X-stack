import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {TrainerProfileDto} from "../../model/TrainerProfileDto";
import {TrainerService} from "../../../service/trainer.service";

export interface TrainerInfo {
  username: string;
  firstname: string;
  lastname: string;


}

@Component({
  selector: 'app-trainer-profile',
  templateUrl: './trainer-profile.component.html',
  styleUrls: ['./trainer-profile.component.css']
})
export class TrainerProfileComponent {
  dataSource: TrainerInfo[] = [];
  trainerProfile: TrainerProfileDto = new TrainerProfileDto();

  constructor(private router: Router, private trainerService: TrainerService) {
  }

  ngOnInit() {
    const state = window.history.state;
    this.trainerProfile = state.profile;
    console.log("we are inside my profile component " + this.trainerProfile);
    if (this.trainerProfile.traineesList) {
      for (const trainee of this.trainerProfile.traineesList) {
        if (trainee.firstName && trainee.userName && trainee.lastName) {
          this.dataSource.push({
            username: trainee.userName,
            firstname: trainee.firstName,
            lastname: trainee.lastName
          });
        }
      }
    }
  }

  updateTrainer() {
    this.router.navigate(['/trainer/trainer-update'], {state: {trainerProfile: this.trainerProfile}});
  }

  updatePassword() {
    this.router.navigate(['/shared/update-password'], {state: {userName: this.trainerProfile.userName}});
  }

  addTraining() {
    this.router.navigate(['/trainer/addTraining'], {state: {trainerProfile: this.trainerProfile}});
  }

  viewTrainings() {
    this.router.navigate(['/trainer/trainer-trainings'], {state: {trainerProfile: this.trainerProfile}});
  }
}
