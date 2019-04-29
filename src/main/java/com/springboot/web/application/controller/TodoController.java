package com.springboot.web.application.controller;



import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springboot.web.application.model.Todo;
import com.springboot.web.application.service.TodoService;
import com.springboot.web.application.service.UserContentService;

@Controller
public class TodoController {
	
	@Autowired
	TodoService todoService;
	
	@Autowired
	UserContentService userDetailsService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	@RequestMapping(value="/list-todos", method=RequestMethod.GET )
	public String showTodo(ModelMap model) {
		String userName = getLoggedInUserName();
		model.put("todosList", todoService.retrieveTodos(userName));
		return "list-todos";
		
	}
	
	@RequestMapping(value="/add-todo", method=RequestMethod.GET )
	public String showAddTodo(ModelMap model) {
		String userName = getLoggedInUserName();
		model.addAttribute("todo", new Todo(0, userName, "Default Desc", new Date(), false,"", ""));
		return "add-todo";
		
	}
	
	@RequestMapping(value="/add-todo", method=RequestMethod.POST )
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		String userName = getLoggedInUserName();
		todoService.addTodo(userName, todo.getDesc(), todo.getTargetDate(), false, userDetailsService.getFirstName(userName), userDetailsService.getLastName(userName));
		return "redirect:/list-todos";	
	}
	
	@RequestMapping(value="/delete-todo", method=RequestMethod.GET )
	public String deleteTodo(ModelMap model, @RequestParam int id) {
		todoService.deleteTodo(id);
		return "redirect:/list-todos";	
	}
	
	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
		Todo todo = todoService.retrieveTodo(id);
		model.put("todo", todo);
		return "add-todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "add-todo";
		}

		todo.setUser(getLoggedInUserName());

		todoService.updateTodo(todo);

		return "redirect:/list-todos";
	}
	
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}

}
