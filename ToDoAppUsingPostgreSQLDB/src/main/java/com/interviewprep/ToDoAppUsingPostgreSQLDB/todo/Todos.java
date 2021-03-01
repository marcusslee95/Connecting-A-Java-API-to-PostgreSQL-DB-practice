package com.interviewprep.ToDoAppUsingPostgreSQLDB.todo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Todos {
	@Id //this is going to be primary key of a record
	@GeneratedValue(strategy = GenerationType.IDENTITY) //and I'm not setting it. Spring you are!
	private int id; 
	private String username; 
	private String description; 
	private boolean isCompleted; 
	private Date targetDate;
	
	public Todos() {
	} 
	
	public Todos(int id, String username, String description, boolean isCompleted, Date targetDate) {
		this.id = id;
		this.username = username;
		this.description = description;
		this.isCompleted = isCompleted;
		this.targetDate = targetDate;
	}

	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getDescription() {
		return description;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	
	
	
	
}

