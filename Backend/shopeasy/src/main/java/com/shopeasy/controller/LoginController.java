package com.shopeasy.controller;

import java.util.HashMap;
import java.util.Map;

import javax.security.auth.login.LoginException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopeasy.model.Login;
import com.shopeasy.model.LoginResponse;
import com.shopeasy.service.LoginService;

@RestController
@RequestMapping("/loginController")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@GetMapping("/")
	public String sayWelcome() {
		return "Welcome to shopeasy";
	}
	
	
	@PostMapping(value="/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Map<String, String>> userLogin(@RequestBody Login login) throws LoginException {
	    LoginResponse response = loginService.loginUser(login);
	    String output = "Customer Logged in Successfully Welcome to shopeasy";
	    
	    Map<String, String> responseMap = new HashMap<>();
	    responseMap.put("sessionKey", response.getSessionkey());
	    responseMap.put("output", output);

	    return new ResponseEntity<Map<String, String>>(responseMap, HttpStatus.ACCEPTED);
	}

	
	@DeleteMapping("/logout/{key}")
	public ResponseEntity<String> userLogout(@PathVariable("key") String key) throws LoginException{
		
		String outPut = loginService.logoutUser(key);
		return new ResponseEntity<String>(outPut,HttpStatus.OK);
		
	}

}
