package com.epam.dto.response;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainerProfileDto {
	private String userName;
	private String firstName;
	private String lastName;
	private String specialization;
	private String email;
	private boolean status;
	private List<TraineeDetailsDto> traineesList;

}
