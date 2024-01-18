package com.epam.restapi;

import com.epam.entity.Training;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.epam.dto.request.TrainingDto;
import com.epam.dto.request.TrainingReportDto;
//import com.epam.kafka.Producer;
import com.epam.service.TrainingServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;


@RestController
@RequestMapping("gym/training")
@Slf4j
@AllArgsConstructor
public class TrainingController {

	private final TrainingServiceImpl trainingServiceImpl;



	@PostMapping
	public void addTraining(@Valid @RequestBody TrainingDto trainingDto)  {
		log.info("Entered into add Training Method RestController :{}", trainingDto);
		TrainingReportDto training = trainingServiceImpl.addTraining(trainingDto);
		log.info("exiting"+training);
//		return new ResponseEntity<>(training,HttpStatus.OK);
		//sqsTemplate.send("gym-Queue",new ObjectMapper().writeValueAsString(reportDto));
	}

	@PostMapping("/health")
	public void sendReport() throws JsonProcessingException {
		ObjectMapper objectMapper=new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		TrainingReportDto trainingDto=new TrainingReportDto("lavanya","sasi","gym",true,"",30,java.time.LocalDate.parse("2001-07-31"));
		//sqsTemplate.send("gym-Queue",objectMapper.writeValueAsString(trainingDto));
	}


}
