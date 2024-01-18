package com.epam.restapi;

import java.util.List;

import com.epam.dto.request.*;
import com.epam.dto.response.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.service.TraineeServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("gym/trainee")
@AllArgsConstructor
public class TraineeController {

	private final TraineeServiceImpl traineeServiceImpl;

	@PostMapping("/registration")
	public ResponseEntity<CredentialsDto> addTrainee(@Valid @RequestBody TraineeDto traineeDto) throws JsonProcessingException {
		log.info("Entered into add trainee Method RestController :{}", traineeDto);
		return new ResponseEntity<>(traineeServiceImpl.addTrainee(traineeDto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<TraineeProfileDto> getTraineeProfile(@RequestParam String username) {
		log.info("Entered into get trainee profile of {} Method RestController  :{}", username);
		TraineeProfileDto t=traineeServiceImpl.getTraineeProfile(username);
		log.info(""+t.isStatus());
		return new ResponseEntity<>(traineeServiceImpl.getTraineeProfile(username), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<TraineeProfileDto> updateTraineeProfile(
			@Valid @RequestBody TraineeUpdateDto traineeProfileUpdate) throws JsonProcessingException {
		log.info("Entered into update trainee profile Method RestController :{}", traineeProfileUpdate);
		return new ResponseEntity<>(traineeServiceImpl.updateTraineeProfile(traineeProfileUpdate), HttpStatus.OK);
	}

	@DeleteMapping
	@ResponseStatus(code = HttpStatus.OK)
	public void deleteTraineeProfile(@RequestParam String username) {
		log.info("Entered into delete trainee profile of {} RestController :{}", username);
		traineeServiceImpl.deleteTrainee(username);
	}

	@PutMapping("/trainers")
	public ResponseEntity<List<TrainerDetailsDto>> updateTraineeTrainers(@RequestBody TraineeTrainersListUpdate traineeTrainersListUpdate) {
		log.info("Entered into update trainee {} trainers List {} Method RestController :{}",traineeTrainersListUpdate);
		return new ResponseEntity<>(traineeServiceImpl.updateTraineeTrainersList(traineeTrainersListUpdate),
				HttpStatus.OK);
	}

	@GetMapping("/notAssignedtrainers")
	public ResponseEntity<List<TrainerDetailsDto>> getNotAssaignedTrainers(@RequestParam String username) {
		log.info("Entered into getNotAssignedTrainers of {} Method RestController :{}", username);
		return new ResponseEntity<>(traineeServiceImpl.getNotAssignedActiveTrainers(username), HttpStatus.OK);
	}

	@PostMapping("/trainings")
	public ResponseEntity<List<TrainingsDetailsResponse>> gettraineeTrainings(@Valid @RequestBody TraineeTrainingsRequestList trainings)
	{
		return  new ResponseEntity<>(traineeServiceImpl.getTraineeTrainings(trainings),HttpStatus.OK);
	}

}
