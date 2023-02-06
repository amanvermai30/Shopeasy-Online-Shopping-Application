package com.shopeasy.service;

import javax.security.auth.login.LoginException;

import com.shopeasy.model.Login;

public interface LoginService {

	public String loginUser(Login credential) throws LoginException;
}
