import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {trainerGuard} from "../guard/trainee.guard";
import {TrainerProfileComponent} from "./trainer-profile/trainer-profile.component";
import {TrainerProfileUpdateComponent} from "./trainer-profile-update/trainer-profile-update.component";
import {AddTrainingComponent} from "./add-training/add-training.component";
import {TrainerTrainingsLogsComponent} from "./trainer-trainings-logs/trainer-trainings-logs.component";

const routes: Routes = [
  {path: "trainer-profile",component: TrainerProfileComponent},
  {path: "trainer-update",component: TrainerProfileUpdateComponent},
  {path: "addTraining", component: AddTrainingComponent},
  {path: "trainer-trainings", component: TrainerTrainingsLogsComponent},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TrainerRoutingModule { }
