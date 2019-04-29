package com.springboot.web.application.service;

import org.springframework.stereotype.Service;

@Service
public class UserContentService {
	
	private UserService userService;
	
	public UserContentService(UserService userService) {
		super();
		this.userService = userService;
	}

	public String getFirstName(String userName) {
		return userService.getFirstName(userName);
	}
	
	public String getLastName(String userName) {
		return userService.getLastName(userName);
	}

}
