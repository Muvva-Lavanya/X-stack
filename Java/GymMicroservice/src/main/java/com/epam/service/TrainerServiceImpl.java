
package com.epam.service;

import java.util.List;

import com.epam.dto.request.TrainerTrainingsRequestList;
import com.epam.dto.response.*;
import com.epam.entity.*;
import com.epam.exception.TraineeException;
import com.epam.repository.TraineeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.dto.request.TrainerDto;
import com.epam.dto.request.TrainerUpdateDto;
import com.epam.exception.TrainerException;
import com.epam.exception.TrainingTypeException;
//import com.epam.kafka.Producer;
import com.epam.repository.TrainerRepository;
import com.epam.repository.TrainingTypeRepository;
import com.epam.repository.UserRepository;
import com.epam.utils.ServiceMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TrainerServiceImpl implements TrainerService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TrainerRepository trainerRepository;
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private TrainingTypeRepository trainingTypeRepository;
	@Autowired
	private ServiceMapper serviceMapper;


//	@Autowired
//	PasswordEncoder encoder;

	static final String TRAINER_EXCEPTION = "Trainer with username not found";

	static final String TRAININGTYPE_EXCEPTION = "Training type with that specialization is not found";

	@Override
	public CredentialsDto addTrainer(TrainerDto trainerDto) throws JsonProcessingException {
		log.info("Entered into Create Trainer Method :{}", trainerDto);
		User user = serviceMapper.createUserTrainerProfile(trainerDto);
		String password = user.getPassword();
//		user.setPassword(encoder.encode(password));
		userRepository.save(user);
		log.info(trainerDto.getSpecialization());
		log.info(""+trainingTypeRepository.findByTrainingTypeName(trainerDto.getSpecialization()));
		TrainingType trainingType = trainingTypeRepository.findByTrainingTypeName(trainerDto.getSpecialization())
				.orElseThrow(() -> new TrainingTypeException(TRAININGTYPE_EXCEPTION));
		Trainer trainer = Trainer.builder().trainingType(trainingType).user(user).build();
		trainerRepository.save(trainer);
		CredentialsDto credentialsDto = CredentialsDto.builder().password(password).username(user.getUsername())
				.build();
		NotificationDto dto = serviceMapper.getRegistrationNotification(credentialsDto, user);
		//sqsTemplate.send("gym-Queue",new ObjectMapper().writeValueAsString(dto));
		log.info("Retriving user credentials");
		return credentialsDto;
	}

	@Override
	public TrainerProfileDto getTrainerProfile(String username) {
		log.info("Entered into get Trainer Profile of {}", username);
		Trainer trainer = findTrainerByUsername(username);
		log.info("Retriving trainer profile");
		return serviceMapper.getTrainerProfile(trainer);
	}

	@Override
	@Transactional
	public TrainerProfileDto updateTrainerProfile(TrainerUpdateDto updateDto) throws JsonProcessingException {
		log.info("Entered into update Trainer Profile of {}", updateDto);
		Trainer trainer = findTrainerByUsername(updateDto.getUsername());
		User user = trainer.getUser();
		user.setStatus(updateDto.isStatus());
		user.setEmail(updateDto.getEmail());
		user.setFirstName(updateDto.getFirstName());
		user.setLastName(updateDto.getLastName());
		updateDto.setSpecialization(trainer.getTrainingType().getTrainingTypeName());
		trainer.setUser(user);
		//NotificationDto dto = serviceMapper.getTrainerUpdateNotification(updateDto);
		//sqsTemplate.send("gym-Queue",new ObjectMapper().writeValueAsString(dto));

		log.info("Retrieving Updated trainer Profile");
		return serviceMapper.getTrainerProfile(trainer);
	}

	@Override
	public List<TrainingsDetailsResponse> getTrainerTrainings(TrainerTrainingsRequestList requestList) {
		log.info("inside getTrainerTrainings method of TrainerServiceImpl with details : {}", requestList);
		Trainer trainer = trainerRepository.findByUserUsername(requestList.getUsername())
				.orElseThrow(() -> new TrainerException("trainer not found"));
		Trainee trainee = null;
		if(requestList.getTraineeName()!=null) {
			trainee = traineeRepository.findByUserUsername(requestList.getTraineeName())
					.orElseThrow(() -> new TraineeException("trainer not found"));
		}
		List<Training> trainingsList = trainerRepository.findAllTrainingInBetween(requestList.getPeriodFrom(),requestList.getPeriodTo(),trainer,trainee);
		log.info("Retriving training details of trainee");
		return serviceMapper.getTrainingsDetailsList(trainingsList);

	}

	private Trainer findTrainerByUsername(String username) {
		return trainerRepository.findByUserUsername(username)
				.orElseThrow(() -> new TrainerException(TRAINER_EXCEPTION + " " + username));
	}
}
