package com.epam.service;

import com.epam.dto.request.UpdatePassword;
import com.epam.dto.response.CredentialsDto;

public interface UserService {
	void changeLogin(UpdatePassword updatePassword);
	 boolean userAuthentication(CredentialsDto credentials);
	

}
