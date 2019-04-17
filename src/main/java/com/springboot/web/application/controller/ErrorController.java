package com.springboot.web.application.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller("error")
public class ErrorController {
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(HttpServletRequest request, Exception exception){
		ModelAndView mv = new ModelAndView();
		mv.addObject("exception", exception.getStackTrace());
		mv.addObject("url", request.getRequestURI());
		mv.setViewName("error");
		return mv;
		
	}
}
