package com.interviewprep.ToDoAppUsingPostgreSQLDB.todo;

import java.util.List;

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
	

}