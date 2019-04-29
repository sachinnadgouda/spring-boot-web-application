package com.springboot.web.application.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public String getFirstName(String userName) {
		//TODO rest call to user service
		return "FirstName";
	}
	
	public String getLastName(String userName) {
		//TODO rest call to user service
		return "LastName";
	}

}
