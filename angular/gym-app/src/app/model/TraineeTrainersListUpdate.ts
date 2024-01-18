export class TraineeTrainersListUpdate {
  traineeUsername: string | undefined;
  trainersUsernames: string[] | undefined;

  constructor(userName: string | undefined, trainers: string[]) {
    this.traineeUsername = userName;
    this.trainersUsernames = trainers;

  }
}
