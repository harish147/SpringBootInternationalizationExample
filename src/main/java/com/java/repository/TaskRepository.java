package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.java.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
	
}
