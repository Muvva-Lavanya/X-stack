package com.epam.service;

import java.util.List;

import com.epam.dto.request.*;
import com.epam.dto.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TraineeService {

	CredentialsDto addTrainee(TraineeDto traineeDto) throws JsonProcessingException;

	TraineeProfileDto getTraineeProfile(String username);

	TraineeProfileDto updateTraineeProfile(TraineeUpdateDto traineeProfileUpdate) throws JsonProcessingException;

	void deleteTrainee(String userName);

	List<TrainerDetailsDto> updateTraineeTrainersList(TraineeTrainersListUpdate traineeTrainersListUpdate);

	List<TrainerDetailsDto> getNotAssignedActiveTrainers(String username);


	List<TrainingsDetailsResponse> getTraineeTrainings(TraineeTrainingsRequestList traineetrainings);
}
