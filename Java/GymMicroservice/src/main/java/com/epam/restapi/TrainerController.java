package com.epam.restapi;

import java.util.List;

import com.epam.dto.request.*;
import com.epam.dto.response.TrainingsDetailsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.response.CredentialsDto;
import com.epam.dto.response.TrainerProfileDto;
import com.epam.dto.response.TrainingDetailsDto;
import com.epam.service.TrainerServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("gym/trainer")
@Slf4j
@AllArgsConstructor
public class TrainerController {

	private final TrainerServiceImpl trainerServiceImpl;

	@PostMapping("/registration")
	public ResponseEntity<CredentialsDto> addTrainer(@Valid @RequestBody TrainerDto trainerDto) throws JsonProcessingException {
		log.info("Entered into add trainer Method RestController :{}", trainerDto);
		return new ResponseEntity<>(trainerServiceImpl.addTrainer(trainerDto), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<TrainerProfileDto> getTrainerProfile(@RequestParam String username) {
		log.info("Entered into get trainer profile of {} Method RestController  :{}", username);
		return new ResponseEntity<>(trainerServiceImpl.getTrainerProfile(username), HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<TrainerProfileDto> updateTrainerProfile(
			@Valid @RequestBody TrainerUpdateDto trainerProfileUpdate) throws JsonProcessingException {
		log.info("Entered into update trainer profile Method RestController :{}", trainerProfileUpdate);
		return new ResponseEntity<>(trainerServiceImpl.updateTrainerProfile(trainerProfileUpdate), HttpStatus.OK);
	}

	@PostMapping("/trainings")
	public ResponseEntity<List<TrainingsDetailsResponse>> gettrainerTrainings(@Valid @RequestBody TrainerTrainingsRequestList trainings)
	{
		log.info("inside gettrainersTrainings method of TrainerRestController with details :{}",trainings);
		return  new ResponseEntity<>(trainerServiceImpl.getTrainerTrainings(trainings),HttpStatus.OK);
	}

}
