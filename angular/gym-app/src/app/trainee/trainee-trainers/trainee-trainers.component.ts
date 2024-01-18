import {Component, OnInit} from '@angular/core';
import {TrainerDetailsDto} from "../../model/TrainerDetailsDto";
import {TraineeProfileDto} from "../../model/TraineeProfileDto";
import {MatTableDataSource} from "@angular/material/table";
import {SelectionModel} from "@angular/cdk/collections";
import {Router} from "@angular/router";
import {TraineeService} from "../../../service/trainee.service";
import {TraineeTrainersListUpdate} from "../../model/TraineeTrainersListUpdate";
import {SnackBarService} from "../../../service/snack-bar.service";

@Component({
  selector: 'app-trainee-trainers',
  templateUrl: './trainee-trainers.component.html',
  styleUrls: ['./trainee-trainers.component.css']
})
export class TraineeTrainersComponent implements OnInit {
  displayedColumns: string[] = ['select', 'username', 'firstName', 'specialization'];

  traineeProfile: TraineeProfileDto = {
    userName: "",
    firstName: "",
    lastName: "",
    dateOfBirth: "",
    address: "",
    email: "",
    status: true,
    trainersList: []

  }

  existingTrainerProfiles: TrainerDetailsDto[] = [];


  dataSource = new MatTableDataSource<TrainerDetailsDto>();
  selection = new SelectionModel<TrainerDetailsDto>(true, []);

  loading = true;
  traineeFirstName: any;

  constructor(private router: Router, private traineeService: TraineeService, private snackbarService: SnackBarService) {
  }

  ngOnInit(): void {
    const state = history.state;
    if (state && state.traineeDetails) {
      this.traineeProfile = state.traineeDetails;
      this.traineeFirstName = this.traineeProfile.firstName;
      if (this.traineeProfile.trainersList) {
        for (const trainer of this.traineeProfile.trainersList) {
          this.existingTrainerProfiles.push(new TrainerDetailsDto(trainer.userName, trainer.firstName, trainer.lastName, trainer.specialization));
        }
      }


      this.fetchFreeTrainers(this.traineeProfile.userName);
      console.log('Received traineeProfile data:', this.traineeProfile);
    } else {
      console.log('No traineeProfile data received.');
    }

  }

  fetchFreeTrainers(username: string | undefined) {
    this.traineeService.getFreeTrainers(username).subscribe(value => {
      console.log(value + " vachae")
      for (const trainer of this.existingTrainerProfiles) {
        this.dataSource.data.push(trainer);
        this.selection.select(trainer);
      }

      // Loop through received trainers and add them to dataSource
      for (const trainer of value) {
        this.dataSource.data.push(trainer);
      }


      this.loading = false;
    })
  }

  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  toggleAllRows() {
    if (this.isAllSelected()) {
      this.selection.clear();
      return;
    }
    this.selection.select(...this.dataSource.data);
  }


  checkboxLabel(row?: TrainerDetailsDto): string {
    if (!row) {
      return `${this.isAllSelected() ? 'deselect' : 'select'} all`;
    }
    return `${this.selection.isSelected(row) ? 'deselect' : 'select'} row ${row.userName}`;
  }

  saveSelectedItems() {
    const selectedElements = this.selection.selected;

    this.traineeProfile.trainersList = [];
    console.log('Selected Elements:');
    let trainers: string[] = [];
    if (selectedElements.length > 0) {
      for (const element of selectedElements) {
        console.log(`Position: ${element.userName}, Name: ${element.firstName}, Weight: ${element.specialization}`);
        if (element.userName != null) {
          this.traineeProfile.trainersList.push(element);
          trainers.push(element.userName);
        }
      }
      let traineeTrainerList = new TraineeTrainersListUpdate(this.traineeProfile.userName, trainers);
      this.traineeService.updateTrainersList(traineeTrainerList).subscribe({
        next:(data:any)=>{
          this.snackbarService.openSnackBar(`Trainers List Updated successfully`);
          this.router.navigate(['/trainee/trainee-profile'], {state: {profile: this.traineeProfile}});
        },error:(error:any)=>{
          this.snackbarService.openSnackBar(error.error.error);
        }
      })
    } else {
      console.log("Please select at least one element");
    }
  }


  backToProfile() {
    this.router.navigate(['trainee-profile'], {state: {profile: this.traineeProfile}});
  }
}
