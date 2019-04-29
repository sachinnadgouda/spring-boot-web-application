package com.springboot.web.application.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.web.application.model.Todo;



@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;
      
	static {
        todos.add(new Todo(1, "User1", "Learn Spring MVC", new Date(),false, "FirstName", "LastName"));
        todos.add(new Todo(2, "User1", "Learn Struts", new Date(), false,  "FirstName", "LastName"));
        todos.add(new Todo(3, "User1", "Learn Hibernate", new Date(),false,  "FirstName", "LastName"));
    }

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equals(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }
    
    public Todo retrieveTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId()==id) {
                return todo;
            }
        }
        return null;
    }
    
    public void updateTodo(Todo todo){
		todos.remove(todo);
		todos.add(todo);
    }

    public void addTodo(String name, String desc, Date targetDate, boolean isDone, String firstName, String lastName) {
        todos.add(new Todo(++todoCount, name, desc, targetDate, isDone, firstName, lastName));
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}