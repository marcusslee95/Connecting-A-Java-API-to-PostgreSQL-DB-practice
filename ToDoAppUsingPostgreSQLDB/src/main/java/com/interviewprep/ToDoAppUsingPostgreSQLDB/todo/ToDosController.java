package com.interviewprep.ToDoAppUsingPostgreSQLDB.todo;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin(origins="http://localhost:4200")
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
//		return null;
		
		throw new NoSuchElementException("User doesn't have a todo of matching id or that user doesn't exist"); //something i'm using to send back error status code
		
//		//could have used the findById method that we have but.... if did that then we wouldn't be checking for username
//		//i.e. only http://localhost:8080/users/M/todos/1 should give me a todo for user M of ID 1 but... 
//		//http://localhost:8080/users/dfasfdsfasdf/todos/1 would give the same todo as previous request url
//		return thingThatInteractsWithDB.findById(id).get();
	}
	
	@DeleteMapping("/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteATodoOfAUser(@PathVariable String username, @PathVariable int id) {

		
		List<Todos> allTodosOfThisUser = thingThatInteractsWithDB.findByUsername(username);
		
		for (Todos todo : allTodosOfThisUser) 
		{ 
		    if (todo.getId() == id) {//there's a user with todo of matching id
		    	thingThatInteractsWithDB.deleteById(id);
		    	return ResponseEntity.noContent().build(); //some 200s status code
		    }
		}
		
		throw new EmptyResultDataAccessException(id); //something i'm using to send back error status code
		
//		//for same reasons as above endpoint didn't use built in JPA method
//		thingThatInteractsWithDB.deleteById(id);
//		return ResponseEntity.noContent().build(); //some 200s status code
		
	}
	
	@PutMapping("/users/{username}/todos/{id}")
	public  ResponseEntity<Todos> updateATodoOfAUser(@PathVariable String username, @PathVariable int id, @RequestBody Todos todo) {

		todo.setUsername(username); //honestly trivial.... just something for people that aren't using UI and just sending requests directly to this web service. in which case we'd want to set username property as whatever they sent in url... as opposed to username value in Todo.... 
		
		Todos updatedOrIfIdDoesntMatchNewlyCreatedTodo = thingThatInteractsWithDB.save(todo);
		return new ResponseEntity<Todos>(updatedOrIfIdDoesntMatchNewlyCreatedTodo, HttpStatus.OK);
		
	}
	
	@PostMapping("/users/{username}/todos")
	public ResponseEntity<Void> createNewTodoForAUser(@PathVariable String username, @RequestBody Todos todo) {
		
		todo.setUsername(username);
		Todos newlyCreatedTodoHopefullySomeoneDidntSendInTodoWithSameId = thingThatInteractsWithDB.save(todo);
		
		//Location of the new Todo we just created -> because that's what we normally send back in response to post request
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(newlyCreatedTodoHopefullySomeoneDidntSendInTodoWithSameId.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}