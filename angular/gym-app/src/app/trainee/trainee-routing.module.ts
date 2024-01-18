import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {traineeGuard} from "../guard/trainer.guard";
import {TraineeProfileComponent} from "./trainee-profile/trainee-profile.component";
import {TraineeProfileUpdateComponent} from "./trainee-profile-update/trainee-profile-update.component";
import {TraineeTrainersComponent} from "./trainee-trainers/trainee-trainers.component";
import {TraineeTrainingsLogsComponent} from "./trainee-trainings-logs/trainee-trainings-logs.component";

const routes: Routes = [
  {path: "trainee-profile", component: TraineeProfileComponent},
  {path: "trainee-update", component: TraineeProfileUpdateComponent},
  {path: "edit-trainee-trainer", component: TraineeTrainersComponent},
  {path: "trainee-trainings", component: TraineeTrainingsLogsComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TraineeRoutingModule { }
