package com.epam.restapi;

import com.epam.dto.response.CredentialsDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.epam.dto.request.UpdatePassword;
import com.epam.service.UserServiceImpl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("gym/user")
@Slf4j
@AllArgsConstructor
public class UserController {
	private final UserServiceImpl userServiceImpl;

//	@PostMapping("/login")
//	@ResponseStatus(code = HttpStatus.OK)
//	public void userLogin(@Valid @RequestBody CredentialsDto credentials)
//	{
//		log.info("Entered into validate Login {}  Method RestController :{}",credentials);
//		userServiceImpl.validateUser(credentials);
//	}

	@PutMapping("/updatePassword")
	@ResponseStatus(code = HttpStatus.OK)
	public void changeUserLogin(@Valid @RequestBody UpdatePassword updatePassword) {
		log.info("Entered into chnage Login {} Method RestController :{}", updatePassword);
		userServiceImpl.changeLogin(updatePassword);
	}

}
