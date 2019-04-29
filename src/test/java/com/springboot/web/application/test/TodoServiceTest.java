package com.springboot.web.application.test;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import com.springboot.web.application.model.Todo;
import com.springboot.web.application.service.TodoService;

public class TodoServiceTest {
	
	TodoService todoService = new TodoService();
	
	@Test
	public void testRetriveTodo(){
		int retriveId = 2;
		Todo todo = todoService.retrieveTodo(retriveId);
		assertEquals(retriveId, todo.getId());
	}
	
	@Test
	public void testRetrive0Todos(){
		String userName = "User2";
		List<Todo> todoList = todoService.retrieveTodos(userName);
		assertEquals(0, todoList.size());
	}
		
	@Test
	public void testAddTodos(){
		String userName = "User1";
		List<Todo> todoList = todoService.retrieveTodos(userName);
		int currentSize = todoList.size();
		todoService.addTodo(userName, "test description", new Date(), false, "", "");
		List<Todo> updatedTodoList = todoService.retrieveTodos(userName);
		assertEquals(currentSize + 1, updatedTodoList.size());
	}
	
	@Test
	public void testDeleteTodos(){
		String userName = "User1";
		List<Todo> todoList = todoService.retrieveTodos(userName);
		int currentSize = todoList.size();
		todoService.deleteTodo(1);
		List<Todo> DepletedTodoList = todoService.retrieveTodos(userName);
		assertEquals(currentSize -1, DepletedTodoList.size());
	}
	
	@Test
	public void testUpdateTodo(){
		String userName = "User1";
		List<Todo> todoList = todoService.retrieveTodos(userName);
		int currentSize = todoList.size();
		String description = "New Description";
		int retriveId = 2;
		Todo todo = todoService.retrieveTodo(retriveId);
		todo.setDesc(description);
		todoService.updateTodo(todo);
		assertEquals(description, todo.getDesc());
		assertEquals(retriveId, todo.getId());
		List<Todo> UpdatedTodoList = todoService.retrieveTodos(userName);
		assertEquals(currentSize, UpdatedTodoList.size());
	}
	

}
