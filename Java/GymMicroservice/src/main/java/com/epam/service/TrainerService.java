package com.epam.service;

import java.util.List;

import com.epam.dto.request.TrainerDto;
import com.epam.dto.request.TrainerTrainingsList;
import com.epam.dto.request.TrainerTrainingsRequestList;
import com.epam.dto.request.TrainerUpdateDto;
import com.epam.dto.response.CredentialsDto;
import com.epam.dto.response.TrainerProfileDto;
import com.epam.dto.response.TrainingDetailsDto;
import com.epam.dto.response.TrainingsDetailsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface TrainerService {
	CredentialsDto addTrainer(TrainerDto trainerDto) throws JsonProcessingException;

	TrainerProfileDto getTrainerProfile(String username);
	
	TrainerProfileDto updateTrainerProfile(TrainerUpdateDto trainerProfileUpdate) throws JsonProcessingException;
	public List<TrainingsDetailsResponse> getTrainerTrainings(TrainerTrainingsRequestList requestList);
}
