package com.epam.service;

import com.epam.dto.response.CredentialsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.epam.dto.request.UpdatePassword;
import com.epam.entity.User;
import com.epam.exception.UserException;
import com.epam.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	

	
	static final String USER_EXCEPTION = "User with username & password not found"; 
	
	
	@Override
	@Transactional
	public void changeLogin(UpdatePassword updatePassword) {
		log.info("Entered into changeLogin method :"+updatePassword);
		User user=userRepository.findByUsernameAndPassword(updatePassword.getUserName(), updatePassword.getOldPassword()).orElseThrow(()->new UserException(USER_EXCEPTION));
		user.setPassword((updatePassword.getNewPassword()));
		
	}

	@Override
	public boolean userAuthentication(CredentialsDto credentials) {

		log.info("inside userAuthentication method of LoginService with details :{}", credentials);

		if (userRepository.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword()).isEmpty()) {
			throw new UserException("invalid credentials");
		}
		return true;
	}

	

}
