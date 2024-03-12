package com.java.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.java.entity.Task;
import com.java.repository.TaskRepository;
import com.java.validation.DateValidations;
import com.java.validation.StringValidations;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	@Override
	public Task getTask(Integer taskId) throws Exception {
		Optional<Task> optionalTask = taskRepository.findById(taskId);
		if (optionalTask.isPresent()) {
			return optionalTask.get();
		} else {
			throw new Exception(
					messageSource.getMessage("exception.task_not_found", null, LocaleContextHolder.getLocale()));
		}
	}

	@Override
	public Task saveTask(Task task) throws Exception {

		if (!StringValidations.validateString(task.getTaskName()))
			throw new Exception(messageSource.getMessage("exception.invalid_field_value", null, LocaleContextHolder.getLocale()) + "taskName");
		if (!StringValidations.validateString(task.getDescription()))
			throw new Exception(messageSource.getMessage("exception.invalid_field_value", null, LocaleContextHolder.getLocale()) + "description");
		if (!DateValidations.dueDateValidation(task.getDueDate()))
			throw new Exception(messageSource.getMessage("exception.invalid_field_value", null, LocaleContextHolder.getLocale()) + "dueDate");
		if (!StringValidations.validateString(task.getStatus()))
			throw new Exception(messageSource.getMessage("exception.invalid_field_value", null, LocaleContextHolder.getLocale()) + "status");

		task.setCreationDate(LocalDate.now());

		return taskRepository.save(task);
	}

	@Override
	public Task updateTask(Task task) throws Exception {
		Optional<Task> optionalCurrentTask = taskRepository.findById(task.getTaskId());
		if (optionalCurrentTask.isPresent()) {
			Task currentTask = optionalCurrentTask.get();

			if (StringValidations.validateString(task.getTaskName()))
				currentTask.setTaskName(task.getTaskName());
			if (StringValidations.validateString(task.getDescription()))
				currentTask.setDescription(task.getDescription());
			if (DateValidations.dueDateValidation(task.getDueDate()))
				currentTask.setDueDate(task.getDueDate());
			if (StringValidations.validateString(task.getStatus()))
				currentTask.setStatus(task.getStatus());
			if (task.getPriorityLevelNumber() != null)
				currentTask.setPriorityLevelNumber(task.getPriorityLevelNumber());

			return taskRepository.save(currentTask);
		} else {
			throw new Exception(
					messageSource.getMessage("exception.task_not_found", null, LocaleContextHolder.getLocale()));
		}
	}

	@Override
	public Boolean deleteTask(Integer taskId) throws Exception {
		Optional<Task> optionalTask = taskRepository.findById(taskId);
		if (optionalTask.isPresent()) {
			taskRepository.delete(optionalTask.get());
			return true;
		} else {
			throw new Exception(
					messageSource.getMessage("exception.task_not_found", null, LocaleContextHolder.getLocale()));
		}
	}

}
