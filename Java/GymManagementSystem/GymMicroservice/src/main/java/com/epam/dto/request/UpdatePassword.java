package com.epam.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePassword {
	@NotBlank(message = "Username is required")
	private String userName;
	@NotBlank(message = "Old Password is required")
	private String oldPassword;
	@NotBlank(message = "New Password is required")
	private String newPassword;

}
