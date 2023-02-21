package com.shopeasy.service;

import javax.security.auth.login.LoginException;

import com.shopeasy.model.Login;
import com.shopeasy.model.LoginResponse;

public interface LoginService {

	public LoginResponse loginUser(Login credential) throws LoginException;
	public String logoutUser(String key) throws LoginException;
}
