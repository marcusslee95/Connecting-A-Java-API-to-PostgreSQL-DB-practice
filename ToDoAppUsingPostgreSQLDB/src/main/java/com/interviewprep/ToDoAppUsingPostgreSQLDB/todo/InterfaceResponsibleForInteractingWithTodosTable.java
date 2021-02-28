package com.interviewprep.ToDoAppUsingPostgreSQLDB.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //this will be used to do db interactions
public interface InterfaceResponsibleForInteractingWithTodosTable extends JpaRepository<Todos, Integer>{

	List<Todos> findByUsername(String username);

}
