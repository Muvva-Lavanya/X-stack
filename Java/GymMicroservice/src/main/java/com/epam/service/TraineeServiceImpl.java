
package com.epam.service;

import java.util.List;
import java.util.Optional;

import com.epam.dto.request.*;
import com.epam.dto.response.*;
import com.epam.entity.*;
import com.epam.exception.TrainingException;
import com.epam.repository.TrainingTypeRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.exception.TraineeException;
//import com.epam.kafka.Producer;
import com.epam.repository.TraineeRepository;
import com.epam.repository.TrainerRepository;
import com.epam.repository.UserRepository;
import com.epam.utils.ServiceMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TraineeServiceImpl implements TraineeService {
	@Autowired
	private TraineeRepository traineeRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TrainerRepository trainerRepository;
	@Autowired
	private ServiceMapper serviceMapper;
	@Autowired
	private TrainingTypeRepository trainingTypeRepository;

	static final String TRAINEE_EXCEPTION = "Trainee with username not found";

	@Override
	public CredentialsDto addTrainee(TraineeDto traineeDto) throws JsonProcessingException {
		log.info("Entered into Create Trainee Method :{}", traineeDto); 
		User user = serviceMapper.createUserTraineeProfile(traineeDto);
		String password = user.getPassword();
		userRepository.save(user);
		Trainee trainee = serviceMapper.createTraineeProfile(traineeDto, user);
		traineeRepository.save(trainee);
		CredentialsDto credentialsDto = CredentialsDto.builder().password(password).username(user.getUsername())
				.build();
		NotificationDto dto = serviceMapper.getRegistrationNotification(credentialsDto, user);
		//sqsTemplate.send("gym-Queue",new ObjectMapper().writeValueAsString(dto));
		return credentialsDto;
	}

	@Override
	public TraineeProfileDto getTraineeProfile(String username) {
		log.info("Entered into get Trainee Profile of {}", username);
		Trainee trainee = findTraineeByUsername(username);
		return serviceMapper.getTraineeProfileDto(trainee);
	}

	@Override
	@Transactional
	public TraineeProfileDto updateTraineeProfile(TraineeUpdateDto traineeUpdateDto) throws JsonProcessingException {
		log.info("Entered into update Trainee Profile of {}", traineeUpdateDto);
		Trainee trainee = findTraineeByUsername(traineeUpdateDto.getUsername());
		User user = trainee.getUser();
		user.setEmail(traineeUpdateDto.getEmail());
		user.setFirstName(traineeUpdateDto.getFirstName());
		user.setLastName(traineeUpdateDto.getLastName());
		user.setStatus(traineeUpdateDto.isStatus());
		trainee.setAddress(traineeUpdateDto.getAddress());
		trainee.setDateOfBirth(traineeUpdateDto.getDateOfBirth());
		NotificationDto dto = serviceMapper.getTraineeUpdateNotification(traineeUpdateDto);
		//sqsTemplate.send("gym-Queue",new ObjectMapper().writeValueAsString(dto));
		log.info("Retrieving Updated trainee Profile"+serviceMapper.getTraineeProfileDto(trainee));
		return serviceMapper.getTraineeProfileDto(trainee);
	}

	@Override
	@Transactional
	public void deleteTrainee(String userName) {
		log.info("Entered into delete trainee profile of {}", userName);
		Trainee trainee = findTraineeByUsername(userName);
		log.info("Deleting the trainee profile");
		traineeRepository.deleteById(trainee.getId());
	}

	@Override
	@Transactional
	public List<TrainerDetailsDto> updateTraineeTrainersList(TraineeTrainersListUpdate traineeTrainersListUpdate) {
		log.info("Entered into update Trainee TrainersList of username {}",traineeTrainersListUpdate.getTraineeUsername() );
		Trainee trainee = findTraineeByUsername(traineeTrainersListUpdate.getTraineeUsername());

		List<Trainer> trainersToAdd = traineeTrainersListUpdate.getTrainersUsernames().stream().map(trainerRepository::findByUserUsername)
				.filter(Optional::isPresent).map(Optional::get)
				.filter(trainer -> !trainee.getTrainerList().contains(trainer)).toList();

		List<Trainer> trainersToRemove = trainee.getTrainerList().stream()
				.filter(trainer -> !traineeTrainersListUpdate.getTrainersUsernames().contains(trainer.getUser().getUsername())).toList();
 
		trainee.getTrainerList().addAll(trainersToAdd);
		trainee.getTrainerList().removeAll(trainersToRemove);
		log.info("Retrieving trainer profile details after updating");
		return serviceMapper.getTrainerDetailsList(trainee.getTrainerList());
	}

	@Override
	public List<TrainerDetailsDto> getNotAssignedActiveTrainers(String username) {
		log.info("Entered into getNotAssignedActiveTrainers of {}", username);
		Trainee trainee = findTraineeByUsername(username);
		List<Trainer> notAssignedTrainers = trainerRepository.findByTraineeListNotContaining(trainee);
		notAssignedTrainers = notAssignedTrainers.stream().filter(t -> t.getUser().isStatus()).toList();
		log.info("Retriving not assigned active trainers");
		notAssignedTrainers.stream().map(t->t.getUser().isStatus()).forEach(System.out::println);
		return serviceMapper.getTrainerDetailsList(notAssignedTrainers);
	}

	@Override
	public List<TrainingsDetailsResponse> getTraineeTrainings(TraineeTrainingsRequestList traineetrainings) {
		log.info("inside method of updateTraineeTrainers in TraineeServiceImpl with username :{}",
				traineetrainings);
		Trainee trainee = traineeRepository.findByUserUsername(traineetrainings.getUsername())
				.orElseThrow(() -> new TrainingException("GYM_EXCEPTION_MESSAGE"));
		Trainer trainer=null;
		if(traineetrainings.getTrainerName()!=null)
		{
			trainer=trainerRepository.findByUserUsername(traineetrainings.getTrainerName()).orElseThrow(() -> new TrainingException("GYM_EXCEPTION_MESSAGE"));
		}
		TrainingType trainingType=null;
		if(traineetrainings.getTrainingType()!=null)
		{
			trainingType=trainingTypeRepository.findByTrainingTypeName(traineetrainings.getTrainingType()).orElseThrow(() -> new TrainingException("GYM_EXCEPTION_MESSAGE"));;

		}

		List<Training> trainingsList = traineeRepository.findAllTrainingInBetween(traineetrainings.getPeriodFrom(),traineetrainings.getPeriodTo(),trainer,trainingType,trainee);
		return serviceMapper.getTrainingsDetailsList(trainingsList);

	}

	private Trainee findTraineeByUsername(String username) {
		return traineeRepository.findByUserUsername(username)
				.orElseThrow(() -> new TraineeException(TRAINEE_EXCEPTION + " " + username));
	}
}
