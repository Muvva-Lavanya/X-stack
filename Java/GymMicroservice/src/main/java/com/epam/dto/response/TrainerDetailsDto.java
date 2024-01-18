package com.epam.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TrainerDetailsDto {
	private String userName;
	private String firstName;
	private String lastName;
	private String specialization;

}
