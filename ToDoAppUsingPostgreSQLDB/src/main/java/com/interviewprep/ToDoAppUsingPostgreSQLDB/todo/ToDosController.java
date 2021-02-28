package com.interviewprep.ToDoAppUsingPostgreSQLDB.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ToDosController {
	
	@GetMapping("/")
	public String sampleEndpoint() {
		return "Testing if this web service is receiving requests properly";
	}
	

}