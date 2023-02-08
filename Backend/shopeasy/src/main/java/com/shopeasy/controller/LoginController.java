package com.shopeasy.controller;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shopeasy.model.Login;
import com.shopeasy.service.LoginService;

@RestController
@RequestMapping("/loginController")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody Login login) throws LoginException{
		
		String outPut = loginService.loginUser(login);
		return new ResponseEntity<String>(outPut,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> userLogout(@RequestParam(required = false) String key) throws LoginException{
		
		String outPut = loginService.logoutUser(key);
		return new ResponseEntity<String>(outPut,HttpStatus.OK);
		
	}

}
