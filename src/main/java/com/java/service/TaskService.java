package com.java.service;

import java.util.List;
import com.java.entity.Task;

public interface TaskService {
	public List<Task> getAllTasks();

	public Task getTask(Integer taskId) throws Exception;

	public Task saveTask(Task task) throws Exception;

	public Task updateTask(Task task) throws Exception;

	public Boolean deleteTask(Integer taskId) throws Exception;
}
