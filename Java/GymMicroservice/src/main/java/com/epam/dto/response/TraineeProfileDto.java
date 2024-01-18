package com.epam.dto.response;

import java.time.LocalDate;
import java.util.List;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@ToString
public class TraineeProfileDto {
	private String userName;
	private String firstName;
	private String lastName;
	private LocalDate dateOfBirth;
	private String address;
	private String email;
	private boolean status;
	private List<TrainerDetailsDto> trainersList;

}
