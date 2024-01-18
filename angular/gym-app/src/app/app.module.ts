import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomepageComponent} from './common-folder/homepage/homepage.component';
import {MatIconModule} from '@angular/material/icon';
import {MatDividerModule} from '@angular/material/divider';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatCardModule} from "@angular/material/card";
import {JoinUsComponent} from './home/join-us/join-us.component';
import {InformationComponent} from './home/information/information.component';
import {TraineeRegistrationComponent} from './home/trainee-registration/trainee-registration.component';
import {TrainerRegistrationComponent} from './home/trainer-registration/trainer-registration.component';
import {MatInputModule} from "@angular/material/input";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatNativeDateModule} from "@angular/material/core";
import {MatSelectModule} from "@angular/material/select";
import {MatRadioModule} from "@angular/material/radio";
import {HttpClientModule} from "@angular/common/http";
import {TrainerProfileComponent} from './trainer/trainer-profile/trainer-profile.component';
import {MatTableModule} from "@angular/material/table";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {TraineeProfileUpdateComponent} from './trainee/trainee-profile-update/trainee-profile-update.component';
import {TrainerProfileUpdateComponent} from './trainer/trainer-profile-update/trainer-profile-update.component';
import {MatSlideToggleModule} from "@angular/material/slide-toggle";
import {DialogBoxComponent} from './common-folder/dialog-box/dialog-box.component';
import {MatDialogModule} from "@angular/material/dialog";
import {PasswordUpdateComponent} from "./shared/password-update/password-update.component";
import {TraineeTrainersComponent} from './trainee/trainee-trainers/trainee-trainers.component';
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {AddTrainingComponent} from './trainer/add-training/add-training.component';
import {TraineeTrainingsLogsComponent} from './trainee/trainee-trainings-logs/trainee-trainings-logs.component';
import {TrainerTrainingsLogsComponent} from './trainer/trainer-trainings-logs/trainer-trainings-logs.component';
import {AboutUsComponent} from './about-us/about-us.component';
import {FooterComponentComponent} from "./common-folder/footer-component/footer-component.component";
import { PageNotFoundComponent } from './common-folder/page-not-found/page-not-found.component';
import {SignInComponent} from "./home/sign-in/sign-in.component";
import {TraineeProfileComponent} from "./trainee/trainee-profile/trainee-profile.component";


@NgModule({
  declarations: [
    AppComponent,
    HomepageComponent,
    JoinUsComponent,
    SignInComponent,
    InformationComponent,
    TraineeRegistrationComponent,
    TrainerRegistrationComponent,
    TraineeProfileComponent,
    TrainerProfileComponent,
    TraineeProfileUpdateComponent,
    TrainerProfileUpdateComponent,
    DialogBoxComponent,
    PasswordUpdateComponent,
    TraineeTrainersComponent,
    AddTrainingComponent,
    TraineeTrainingsLogsComponent,
    TrainerTrainingsLogsComponent,
    AboutUsComponent,
    FooterComponentComponent,
    PageNotFoundComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatDialogModule,
    MatButtonModule, MatSnackBarModule, HttpClientModule, MatDividerModule, MatIconModule, MatToolbarModule, BrowserAnimationsModule, MatCardModule, MatInputModule, MatDatepickerModule, FormsModule, MatNativeDateModule, MatSelectModule, MatRadioModule, ReactiveFormsModule, MatTableModule, MatSlideToggleModule, MatProgressSpinnerModule, MatCheckboxModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
