import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {InformationComponent} from "./information/information.component";
import {JoinUsComponent} from "./join-us/join-us.component";
import {SignInComponent} from "./sign-in/sign-in.component";
import {TraineeRegistrationComponent} from "./trainee-registration/trainee-registration.component";
import {TrainerRegistrationComponent} from "./trainer-registration/trainer-registration.component";

const routes: Routes = [
  {path: "", component: InformationComponent},
  {path: "join-us", component: JoinUsComponent},
  {path: "signIn", component: SignInComponent},
  {path: "trainee-signup", component: TraineeRegistrationComponent},
  {path: "trainer-signup", component: TrainerRegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
