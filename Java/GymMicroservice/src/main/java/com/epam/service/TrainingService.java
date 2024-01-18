package com.epam.service;

import com.epam.dto.request.TraineeTrainingsRequestList;
import com.epam.dto.request.TrainingDto;
import com.epam.dto.request.TrainingReportDto;
import com.epam.dto.response.TrainingsDetailsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface TrainingService {
	TrainingReportDto addTraining(TrainingDto trainingDto) throws JsonProcessingException;


}
