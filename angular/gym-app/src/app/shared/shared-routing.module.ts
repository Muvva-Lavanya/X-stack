import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {UpdatePassword} from "../model/UpdatePassword";
import {PasswordUpdateComponent} from "./password-update/password-update.component";


const routes: Routes = [{
  path: "update-password",
  component:PasswordUpdateComponent
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SharedRoutingModule { }
