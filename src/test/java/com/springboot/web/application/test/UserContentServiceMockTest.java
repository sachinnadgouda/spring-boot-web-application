package com.springboot.web.application.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.springboot.web.application.service.UserContentService;
import com.springboot.web.application.service.UserService;


@RunWith(MockitoJUnitRunner.class)
public class UserContentServiceMockTest {
	

	String firstName = "FirstName";	
	String LastName = "LastName";
	
	@Mock
	UserService userServiceMock;
	
	@InjectMocks
	UserContentService userContentService;
	
	@Test
	public void testGetFirstName(){
		when(userServiceMock.getFirstName(Mockito.anyString())).thenReturn(firstName);
		assertEquals(firstName, userContentService.getFirstName(""));	
	}
	
	@Test
	public void testGetLastName(){		
		when(userServiceMock.getLastName(Mockito.anyString())).thenReturn(LastName);
		assertEquals(LastName, userContentService.getLastName(""));
	}

}
