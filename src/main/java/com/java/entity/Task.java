package com.java.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "task")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer taskId;

	private String taskName;

	private String description;

	private LocalDate creationDate;

	private LocalDate dueDate;

	private String status;

	private Integer priorityLevelNumber;

	public Task() {
	}

	public Task(Integer taskId, String taskName, String description, LocalDate creationDate, LocalDate dueDate,
			String status, Integer priorityLevelNumber) {
		this.taskId = taskId;
		this.taskName = taskName;
		this.description = description;
		this.creationDate = creationDate;
		this.dueDate = dueDate;
		this.status = status;
		this.priorityLevelNumber = priorityLevelNumber;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getPriorityLevelNumber() {
		return priorityLevelNumber;
	}

	public void setPriorityLevelNumber(Integer priorityLevelNumber) {
		this.priorityLevelNumber = priorityLevelNumber;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", taskName=" + taskName + ", description=" + description + ", creationDate="
				+ creationDate + ", dueDate=" + dueDate + ", status=" + status + ", priorityLevelNumber="
				+ priorityLevelNumber + "]";
	}

}
