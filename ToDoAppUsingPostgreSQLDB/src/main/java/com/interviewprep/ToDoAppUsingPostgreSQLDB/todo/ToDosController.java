package com.interviewprep.ToDoAppUsingPostgreSQLDB.todo;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDosController {
	
	@Autowired 
	InterfaceResponsibleForInteractingWithTodosTable thingThatInteractsWithDB;
	
	@GetMapping("/")
	public String sampleEndpoint() {
		return "Testing if this web service is receiving requests properly";
	}
	
	@GetMapping("/todos")
	public List<Todos> findAllToDos() {
		return thingThatInteractsWithDB.findAll();
	}
	
	@GetMapping("/users/{username}/todos")
	public List<Todos> findAllToDosOfAUser(@PathVariable String username) {
//		return "Username is: " + username;
//		return thingThatInteractsWithDB.findAll();
		return thingThatInteractsWithDB.findByUsername(username);
	}
	
	@GetMapping("/users/{username}/todos/{id}")
	public Todos findAToDoOfAUser(@PathVariable String username, @PathVariable int id) {
		List<Todos> allTodosOfThisUser = thingThatInteractsWithDB.findByUsername(username);
		
		for (Todos todo : allTodosOfThisUser) 
		{ 
		    if (todo.getId() == id) {
		    	return todo;
		    }
		}
		
		throw new NoSuchElementException("User doesn't have a todo of matching id or that user doesn't exist");
		
//		//could have used the findById method that we have but.... if did that then we wouldn't be checking for username
//		//i.e. only http://localhost:8080/users/M/todos/1 should give me a todo for user M of ID 1 but... 
//		//http://localhost:8080/users/dfasfdsfasdf/todos/1 would give the same todo as previous request url
//		return thingThatInteractsWithDB.findById(id).get();
	}
	

}